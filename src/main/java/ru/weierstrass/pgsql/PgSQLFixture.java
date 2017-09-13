package ru.weierstrass.pgsql;

import ru.weierstrass.fixture.Fixture;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PgSQLFixture extends Fixture {

    String getTableName();

    List<Map<String, Object>> getData();

    default List<PgSQLFixtureSequence> getSequences() {
        return new ArrayList<>();
    }

}
