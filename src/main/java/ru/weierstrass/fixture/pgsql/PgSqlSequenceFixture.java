package ru.weierstrass.fixture.pgsql;

public interface PgSqlSequenceFixture {

    String getName();

    String getTableName();

    String getSequenceColumnName();

    Integer getInitialValue();

}
