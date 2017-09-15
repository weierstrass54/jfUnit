package ru.weierstrass.exception;

public class FixtureAlreadyLoadedException extends FixtureLoadingException {

    public FixtureAlreadyLoadedException( String message ) {
        super( message );
    }

    public FixtureAlreadyLoadedException( Throwable cause ) {
        super( cause );
    }

}
