package ru.weierstrass.loader;

import ru.weierstrass.db.JdbcQuery;
import ru.weierstrass.exception.FixtureLoadingException;
import ru.weierstrass.fixture.pgsql.PgSqlSequenceFixture;

public class FixtureLoaderToPgSql extends FixtureLoaderToDb<LoadableToPgSql> {

    public FixtureLoaderToPgSql( JdbcQuery jdbcQuery ) {
        super( jdbcQuery );
    }

    @Override
    public void load( LoadableToPgSql fixture ) throws FixtureLoadingException {
        super.load( fixture );
        fixture.getSequences().forEach( this::resetSequence );
    }

    private void resetSequence( PgSqlSequenceFixture sequence ) {
        String query = String.format(
            "SELECT SETVAL( '%s', %s ) FROM %s",
            sequence.getName(),
            sequence.getInitialValue() != null ? sequence.getInitialValue() : "MAX( " + sequence.getSequenceColumnName() + " )",
            sequence.getTableName()
        );
        jdbcQuery.execute( query );
    }

}
