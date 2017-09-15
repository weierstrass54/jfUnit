package ru.weierstrass.loader;

import ru.weierstrass.fixture.pgsql.PgSqlSequenceFixture;

import java.util.ArrayList;
import java.util.List;

public interface LoadableToPgSql extends LoadableToDb {

    default List<PgSqlSequenceFixture> getSequences() {
        return new ArrayList<>();
    }

}
