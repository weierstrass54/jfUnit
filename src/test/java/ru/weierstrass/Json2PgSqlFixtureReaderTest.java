package ru.weierstrass;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import ru.weierstrass.fixture.pgsql.JsonFileToPgSqlFixture;
import ru.weierstrass.fixture.readable.JsonFile2PgSqlFixture1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@Slf4j
public class Json2PgSqlFixtureReaderTest {

    @Test
    public void readAndSerializeTest() {
        JsonFileToPgSqlFixture fixture = new JsonFile2PgSqlFixture1();

        List<Map<String, Object>> contents = fixture.getData();
        log.debug( "contents: {}", contents );

        List<Map<String, Object>> expected = new ArrayList<Map<String, Object>>() {{
            add( new HashMap<String, Object>() {{
                put( "id", 1 );
                put( "name", "Hello" );
                put( "type", 3 );
            }} );

            add( new HashMap<String, Object>() {{
                put( "id", 2 );
                put( "name", "Goodbye" );
                put( "type", 2 );
            }} );

            add( new HashMap<String, Object>() {{
                put( "id", 3 );
                put( "name", "Leave" );
                put( "type", 1 );
            }} );
        }};

        assertEquals( expected, contents );
    }

}
