package ru.weierstrass.pgsql;

import ru.weierstrass.db.JdbcQuery;
import ru.weierstrass.fixture.FixtureLoader;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PgSQLFixtureLoader implements FixtureLoader<PgSQLFixture> {

    private final Set<String> loadedTables = new HashSet<>();

    private final JdbcQuery jdbcQuery;

    public PgSQLFixtureLoader( JdbcQuery jdbcQuery ) {
        this.jdbcQuery = jdbcQuery;
    }

    @Override
    public void load( PgSQLFixture fixture ) throws Exception {
        if( loadedTables.contains( fixture.getTableName() ) ) {
            throw new Exception();
        }
        loadedTables.add( fixture.getTableName() );
        insert( fixture );
        fixture.getSequences().forEach( this::resetSequence );
    }

    private void insert( PgSQLFixture fixture ) {
        for( Map<String, Object> row : fixture.getData() ) {
            int size = row.size();
            int i = 0;
            String[] columns = new String[size];
            Object[] params = new Object[size];
            for( Map.Entry<String, Object> entry : row.entrySet() ) {
                columns[i] = String.format( "\"%s\"", entry.getKey() );
                params[i] = entry.getValue();
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

    private void resetSequence( PgSQLFixtureSequence sequence ) {
        String query = String.format(
            "SELECT SETVAL( '%s', %s ) FROM %s",
            sequence.getName(),
            sequence.getInitialValue() != null ? sequence.getInitialValue() : "MAX( " + sequence.getSequenceColumnName() + " )",
            sequence.getTableName()
        );
        jdbcQuery.execute( query );
    }

}
