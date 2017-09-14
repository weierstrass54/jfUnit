package ru.weierstrass.fixture;

public class FixtureLoaderNotFound extends FixtureLoadingException {

    public FixtureLoaderNotFound( String message ) {
        super( message );
    }

    public FixtureLoaderNotFound( Throwable cause ) {
        super( cause );
    }

}