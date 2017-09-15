package ru.weierstrass.fixture.pgsql;

import ru.weierstrass.loader.LoadableToPgSql;
import ru.weierstrass.reader.ReadableFromFile;
import ru.weierstrass.serializer.JsonSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class JsonFileToPgSqlFixture implements ReadableFromFile, LoadableToPgSql {

    private JsonSerializer serializer = new JsonSerializer();

    @Override
    @SuppressWarnings( "unchecked" )
    public List<Map<String, Object>> getData() {
        try {
            return serializer.getObjectMapper().readValue( read(), List.class );
        }
        catch( IOException e ) {
            return new ArrayList<>();
        }
    }

}
