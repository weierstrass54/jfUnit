package ru.weierstrass.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer implements JacksonSerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public ObjectMapper getObjectMapper() {
        return JsonSerializer.mapper;
    }

}
