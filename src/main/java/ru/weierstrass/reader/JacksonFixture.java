package ru.weierstrass.reader;

import com.fasterxml.jackson.databind.ObjectMapper;

abstract public class JacksonFixture<T> implements Readable<T> {

    abstract protected ObjectMapper getMapper();

    @Override
    public T read() {
        return null;
    }

}
