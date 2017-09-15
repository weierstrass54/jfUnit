package ru.weierstrass.exception;

public class FixtureLoadingException extends Exception {

    public FixtureLoadingException( String message ) {
        super( message );
    }

    public FixtureLoadingException( Throwable cause ) {
        super( cause );
    }

}
