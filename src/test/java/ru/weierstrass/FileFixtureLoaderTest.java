package ru.weierstrass;

import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Test;
import ru.weierstrass.file.FileFixture;
import ru.weierstrass.file.FileFixtureLoader;
import ru.weierstrass.fixture.FixtureAlreadyLoadedException;
import ru.weierstrass.fixture.FixtureLoadingException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class FileFixtureLoaderTest {

    @Test
    public void testFileLoader() throws Exception {
        Path fixturePath = Paths.get( "/tmp/jfUnit/test/fixture.txt" );
        String fixtureContent = "Hello, world!";

        FileFixture fixture = mock( FileFixture.class );
        when( fixture.getFilePath() ).thenReturn( fixturePath.toString() );
        when( fixture.getData() ).thenReturn( fixtureContent );

        FileFixtureLoader loader = new FileFixtureLoader();
        loader.load( fixture );

        assertTrue( Files.exists( fixturePath ) );
        assertEquals( fixtureContent, new String( Files.readAllBytes( fixturePath ) ) );
    }

    @Test( expected = FixtureAlreadyLoadedException.class )
    public void testFileLoaderDuplicate() throws FixtureLoadingException {
        Path fixturePath = Paths.get( "/tmp/jfUnit/test/fixture.txt" );
        String fixtureContent = "Hello, world!";

        FileFixture fixture = mock( FileFixture.class );
        when( fixture.getFilePath() ).thenReturn( fixturePath.toString() );
        when( fixture.getData() ).thenReturn( fixtureContent );

        FileFixtureLoader loader = new FileFixtureLoader();
        loader.load( fixture );
        loader.load( fixture );
    }

    @After
    @SneakyThrows
    public void cleanup() {
        Files.deleteIfExists( Paths.get( "/tmp/jfUnit/test/fixture.txt" ) );
    }

}
