package ru.weierstrass.reader;

public interface Readable<T> {

    T read() throws Exception;

}
