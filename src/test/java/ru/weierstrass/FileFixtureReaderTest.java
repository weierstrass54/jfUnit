package ru.weierstrass;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import ru.weierstrass.fixture.readable.ReadableFromFileFixture1;
import ru.weierstrass.reader.ReadableFromFile;

import java.io.IOException;

import static org.junit.Assert.*;

@Slf4j
public class FileFixtureReaderTest {

    @Test
    public void readTest() throws IOException {
        ReadableFromFile fixture = new ReadableFromFileFixture1();

        String content = new String( fixture.read() );

        assertEquals( "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Hello\",\n" +
            "    \"type\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"name\": \"Goodbye\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 3,\n" +
            "    \"name\": \"Leave\",\n" +
            "    \"type\": 1\n" +
            "  }\n" +
            "]", content );
    }

}
