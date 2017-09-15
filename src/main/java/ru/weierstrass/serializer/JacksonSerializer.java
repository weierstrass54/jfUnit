package ru.weierstrass.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface JacksonSerializer {

    ObjectMapper getObjectMapper();

}
