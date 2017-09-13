package ru.weierstrass;

import ru.weierstrass.fixture.FixtureManager;

public class Application {

    public static void main( String[] args ) {
        FixtureManager manager = FixtureManager
            .builder()
                .withFile()
            .build();
    }

}
