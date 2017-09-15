package ru.weierstrass.fixture.readable;

import ru.weierstrass.fixture.pgsql.JsonFileToPgSqlFixture;

public class JsonFile2PgSqlFixture1 extends JsonFileToPgSqlFixture {

    @Override
    public String getTableName() {
        return "test";
    }

    @Override
    public String getFileFromPath() {
        return getClass().getClassLoader().getResource( "data/test.json" ).getPath();
    }

}
