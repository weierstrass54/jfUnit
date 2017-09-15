package ru.weierstrass;

import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Test;
import ru.weierstrass.loader.LoadableToFile;
import ru.weierstrass.loader.FixtureLoaderToFile;
import ru.weierstrass.exception.FixtureAlreadyLoadedException;
import ru.weierstrass.exception.FixtureLoadingException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class FixtureLoaderToFileTest {

    @Test
    public void testFileLoader() throws Exception {
        Path fixturePath = Paths.get( "/tmp/jfUnit/test/fixture.txt" );
        String fixtureContent = "Hello, world!";

        LoadableToFile fixture = mock( LoadableToFile.class );
        when( fixture.getFileToPath() ).thenReturn( fixturePath.toString() );
        when( fixture.getData() ).thenReturn( fixtureContent );

        FixtureLoaderToFile loader = new FixtureLoaderToFile();
        loader.load( fixture );

        assertTrue( Files.exists( fixturePath ) );
        assertEquals( fixtureContent, new String( Files.readAllBytes( fixturePath ) ) );
    }

    @Test( expected = FixtureAlreadyLoadedException.class )
    public void testFileLoaderDuplicate() throws FixtureLoadingException {
        Path fixturePath = Paths.get( "/tmp/jfUnit/test/fixture.txt" );
        String fixtureContent = "Hello, world!";

        LoadableToFile fixture = mock( LoadableToFile.class );
        when( fixture.getFileToPath() ).thenReturn( fixturePath.toString() );
        when( fixture.getData() ).thenReturn( fixtureContent );

        FixtureLoaderToFile loader = new FixtureLoaderToFile();
        loader.load( fixture );
        loader.load( fixture );
    }

    @After
    @SneakyThrows
    public void cleanup() {
        Files.deleteIfExists( Paths.get( "/tmp/jfUnit/test/fixture.txt" ) );
    }

}
