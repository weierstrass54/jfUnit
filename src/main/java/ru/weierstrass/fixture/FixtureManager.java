package ru.weierstrass.fixture;

import lombok.extern.slf4j.Slf4j;
import ru.weierstrass.db.JdbcQuery;
import ru.weierstrass.file.FileFixture;
import ru.weierstrass.file.FileFixtureLoader;
import ru.weierstrass.pgsql.PgSQLFixture;
import ru.weierstrass.pgsql.PgSQLFixtureLoader;

import java.util.*;

@Slf4j
public class FixtureManager {

    private final List<Class<? extends Fixture>> queue;
    private final Map<Class<? extends Fixture>, FixtureLoader> loaders;

    private Class<? extends Fixture> currentFixture;

    private FixtureManager() {
        queue = new ArrayList<>();
        loaders = new HashMap<>();
    }

    private void appendLoader( Class<? extends Fixture> clazz, FixtureLoader loader ) {
        loaders.put( clazz, loader );
    }

    public void loadFixtures( Set<Class<? extends Fixture>> fixtures ) throws FixtureLoadingException {
        for( Class<? extends Fixture> fixture : fixtures ) {
            addToQueue( fixture );
            try {
                Fixture instance = fixture.newInstance();
                beforeLoad( instance );
                loadDependencies( instance );
                loadFixture( instance );
                afterLoad( instance );
            }
            catch( InstantiationException | IllegalAccessException e ) {
                throw new FixtureLoadingException( e );
            }
        }
        queue.clear();
    }

    // this might be useful...
    protected void beforeLoad( Fixture fixture ) {
    }

    // this might be useful...
    protected void afterLoad( Fixture fixture ) {
    }

    private void addToQueue( Class<? extends Fixture> fixture ) throws FixtureCircularDependencyException {
        if( queue.contains( fixture ) ) {
            showCircualarException( fixture );
        }
        queue.add( fixture );
    }

    private void loadDependencies( Fixture fixture ) throws FixtureLoadingException {
        currentFixture = fixture.getClass();
        if( !fixture.getDependencies().isEmpty() ) {
            loadFixtures( fixture.getDependencies() );
        }
    }

    @SuppressWarnings( "unchecked" )
    private void loadFixture( Fixture fixture ) throws FixtureLoadingException {
        getSuitableLoader( fixture.getClass() ).load( fixture );
    }

    private FixtureLoader getSuitableLoader( Class<? extends Fixture> clazz ) throws FixtureLoaderNotFound {
        FixtureLoader loader = null;
        Class<?>[] interfaces = clazz.getInterfaces();
        for( int i = 0; i < interfaces.length && loader == null; ++i ) {
            loader = loaders.get( interfaces[i] );
        }
        if( loader == null ) {
            throw new FixtureLoaderNotFound( "Не удалось найти загрузчик для фикстуры " + clazz.getName() );
        }
        return loader;
    }

    private void showCircualarException( Class<? extends Fixture> fixture ) throws FixtureCircularDependencyException {
        int index = queue.lastIndexOf( fixture ) + 1;
        if( index == queue.size() ) {
            throw new FixtureCircularDependencyException( "Обнаружена рефлексивная зависимость фикстуры " + fixture.getClass() );
        }
        StringJoiner sj = new StringJoiner( " -> " );
        sj.add( fixture.getName() );
        for( Class<? extends Fixture> dependency : queue.subList( index, queue.size() - 1 ) ) {
            sj.add( dependency.getName() );
        }
        sj.add( currentFixture.getName() ).add( fixture.getName() ).add( "..." );
        log.debug( "circular: {}", sj.toString() );
        throw new FixtureCircularDependencyException( "Обнаружена циркулярная зависимость фикстуры " + fixture.getClass() );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private JdbcQuery jdbc;
        private boolean withFile;

        private Builder() {
            withFile = false;
        }

        public Builder withFile() {
            withFile = true;
            return this;
        }

        public Builder withJdbc( JdbcQuery query ) {
            jdbc = query;
            return this;
        }

        public FixtureManager build() {
            FixtureManager manager = new FixtureManager();
            if( withFile ) {
                manager.appendLoader( FileFixture.class, new FileFixtureLoader() );
            }
            if( jdbc != null ) {
                manager.appendLoader( PgSQLFixture.class, new PgSQLFixtureLoader( jdbc ) );
            }
            return manager;
        }

    }

}
