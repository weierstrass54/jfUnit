package ru.weierstrass.loader;

import ru.weierstrass.fixture.Fixture;

public interface LoadableToFile extends Fixture {

    String getFileToPath();

    String getData();

}
