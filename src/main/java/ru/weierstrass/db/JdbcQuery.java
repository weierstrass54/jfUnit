package ru.weierstrass.db;

public interface JdbcQuery {

    void insert( String query, Object... params );
    void execute( String query, Object... params );

}
