package ru.weierstrass.loader;

import ru.weierstrass.db.JdbcQuery;
import ru.weierstrass.exception.FixtureAlreadyLoadedException;
import ru.weierstrass.exception.FixtureLoadingException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract public class FixtureLoaderToDb<T extends LoadableToDb> implements FixtureLoader<T> {

    private final Set<String> loadedTables = new HashSet<>();

    protected final JdbcQuery jdbcQuery;

    protected FixtureLoaderToDb( JdbcQuery jdbcQuery ) {
        this.jdbcQuery = jdbcQuery;
    }

    @Override
    public void load( T fixture ) throws FixtureLoadingException {
        if( loadedTables.contains( fixture.getTableName() ) ) {
            throw new FixtureAlreadyLoadedException( "Таблица " + fixture.getTableName() + " уже загружена." );
        }
        loadedTables.add( fixture.getTableName() );
        insert( fixture );
    }

    protected void insert( LoadableToDb fixture ) {
        for( Map<String, Object> row : fixture.getData() ) {
            int size = row.size();
            int i = 0;
            String[] columns = new String[size];
            Object[] params = new Object[size];
            for( Map.Entry<String, Object> entry : row.entrySet() ) {
                columns[i] = String.format( "\"%s\"", entry.getKey() );
                params[i] = entry.getValue();
                i++;
            }
            String query = String.format(
                "INSERT INTO %s (%s) VALUES (%s)",
                fixture.getTableName(),
                String.join( ",", columns ),
                String.join( ",", Collections.nCopies( size, "?" ) )
            );
            jdbcQuery.insert( query, params );
        }
    }

}
