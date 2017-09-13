package ru.weierstrass.pgsql;

public interface PgSQLFixtureSequence {

    String getName();

    String getTableName();

    String getSequenceColumnName();

    Integer getInitialValue();

}
