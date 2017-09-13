package ru.weierstrass.fixture;

public interface FixtureLoader<T extends Fixture> {

    void load( T fixture ) throws Exception;

}
