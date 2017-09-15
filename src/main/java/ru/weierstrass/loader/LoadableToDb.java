package ru.weierstrass.loader;

import ru.weierstrass.fixture.Fixture;

import java.util.List;
import java.util.Map;

public interface LoadableToDb extends Fixture {

    String getTableName();

    List<Map<String, Object>> getData();

}
