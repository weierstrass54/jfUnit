package ru.weierstrass.fixture;

import ru.weierstrass.db.JdbcQuery;
import ru.weierstrass.file.FileFixture;
import ru.weierstrass.file.FileFixtureLoader;
import ru.weierstrass.pgsql.PgSQLFixture;
import ru.weierstrass.pgsql.PgSQLFixtureLoader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FixtureManager {

    private final Set<Fixture> queue;
    private final Map<Class<? extends Fixture>, FixtureLoader> loaders;

    private FixtureManager() {
        queue = new HashSet<>();
        loaders = new HashMap<>();
    }

    private void appendLoader( Class<? extends Fixture> clazz, FixtureLoader loader ) {
        loaders.put( clazz, loader );
    }

    private void loadFixtures( Set<Class<? extends Fixture>> fixtures ) throws Exception {
        for( Class<? extends Fixture> fixture : fixtures ) {
            Fixture instance = fixture.newInstance();
            addToQueue( instance );
            beforeLoad( instance );
            loadDependencies( instance );
            loadFixture( instance );
            afterLoad( instance );
        }
    }

    private void addToQueue( Fixture fixture ) throws Exception {
        if( queue.contains( fixture ) ) {
            throw new Exception();
        }
        queue.add( fixture );
    }

    private void loadDependencies( Fixture fixture ) throws Exception {
        if( !fixture.getDependencies().isEmpty() ) {
            loadFixtures( fixture.getDependencies() );
        }
    }

    @SuppressWarnings( "unchecked" )
    private void loadFixture( Fixture fixture ) throws Exception {
        loaders.get( fixture.getClass() ).load( fixture );
    }

    // this might be useful...
    protected void beforeLoad( Fixture fixture ) {
    }

    // this might be useful...
    protected void afterLoad( Fixture fixture ) {
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
