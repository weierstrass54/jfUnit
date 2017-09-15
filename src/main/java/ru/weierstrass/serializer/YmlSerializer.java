package ru.weierstrass.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YmlSerializer implements JacksonSerializer {

    private static final ObjectMapper mapper = new ObjectMapper( new YAMLFactory() );

    @Override
    public ObjectMapper getObjectMapper() {
        return YmlSerializer.mapper;
    }

}
