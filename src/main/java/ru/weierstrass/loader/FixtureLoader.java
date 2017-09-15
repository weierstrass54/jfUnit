package ru.weierstrass.loader;

import ru.weierstrass.exception.FixtureLoadingException;
import ru.weierstrass.fixture.Fixture;

public interface FixtureLoader<T extends Fixture> {

    void load( T fixture ) throws FixtureLoadingException;

}
