package ru.weierstrass.fixture;

public class FixtureCircularDependencyException extends FixtureLoadingException {

    public FixtureCircularDependencyException( String message ) {
        super( message );
    }

    public FixtureCircularDependencyException( Throwable cause ) {
        super( cause );
    }

}
