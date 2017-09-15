package ru.weierstrass.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;

public class XmlSerializer implements JacksonSerializer {

    private static final ObjectMapper mapper = new ObjectMapper( new XmlFactory() );

    @Override
    public ObjectMapper getObjectMapper() {
        return XmlSerializer.mapper;
    }

}
