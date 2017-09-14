package ru.weierstrass;

import org.junit.After;
import org.junit.Test;
import ru.weierstrass.fixture.Fixture;
import ru.weierstrass.fixture.FixtureCircularDependencyException;
import ru.weierstrass.fixture.FixtureManager;
import ru.weierstrass.fixture.file.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class FixtureManagerTest {

    @Test
    public void fixtureManagerFileTest() throws Exception {
        FixtureManager manager = FixtureManager
            .builder()
                .withFile()
            .build();
        manager.loadFixtures( getSimpleFixtures() );

        Path path = null;

        path = Paths.get( "/tmp/jfUnit/test/file1.txt" );
        assertTrue( Files.exists( path ) );
        assertEquals( "Hello, world!", new String( Files.readAllBytes( path ) ) );

        path = Paths.get( "/tmp/jfUnit/test/file2.txt" );
        assertTrue( Files.exists( path ) );
        assertEquals( "Goodbye, world!", new String( Files.readAllBytes( path ) ) );

        path = Paths.get( "/tmp/jfUnit/test/file4.txt" );
        assertTrue( Files.exists( path ) );
        assertEquals( "I hate you, world!", new String( Files.readAllBytes( path ) ) );
    }

    private Set<Class<? extends Fixture>> getSimpleFixtures() {
        return new HashSet<Class<? extends Fixture>>() {{
            add( FileFixture1.class );
            add( FileFixture2.class );
            add( FileFixture4.class );
        }};
    }

    @Test
    public void fixtureManagerFileWithDependenciesTest() throws Exception {
        FixtureManager manager = FixtureManager
            .builder()
            .withFile()
            .build();
        manager.loadFixtures( getFixturesWithDependencies() );

        Path path = null;

        path = Paths.get( "/tmp/jfUnit/test/file1.txt" );
        assertTrue( Files.exists( path ) );
        assertEquals( "Hello, world!", new String( Files.readAllBytes( path ) ) );

        path = Paths.get( "/tmp/jfUnit/test/file2.txt" );
        assertTrue( Files.exists( path ) );
        assertEquals( "Goodbye, world!", new String( Files.readAllBytes( path ) ) );

        path = Paths.get( "/tmp/jfUnit/test/file3.txt" );
        assertTrue( Files.exists( path ) );
        assertEquals( "I love you, world!", new String( Files.readAllBytes( path ) ) );

        path = Paths.get( "/tmp/jfUnit/test/file4.txt" );
        assertTrue( Files.exists( path ) );
        assertEquals( "I hate you, world!", new String( Files.readAllBytes( path ) ) );
    }

    private Set<Class<? extends Fixture>> getFixturesWithDependencies() {
        return new HashSet<Class<? extends Fixture>>() {{
            add( FileFixture1.class );
            add( FileFixture2.class );
            add( FileFixture3.class );
        }};
    }

    @Test( expected = FixtureCircularDependencyException.class )
    public void fixtureManagerFileWithCircularDependenciesTest() throws Exception {
        FixtureManager manager = FixtureManager
            .builder()
            .withFile()
            .build();

        manager.loadFixtures( getFixturesWithCircularDependencies() );
    }

    private Set<Class<? extends Fixture>> getFixturesWithCircularDependencies() {
        return new HashSet<Class<? extends Fixture>>() {{
            add( FileFixture1.class );
            add( FileFixture5.class );
            add( FileFixture2.class );
            add( FileFixture6.class );
            add( FileFixture7.class );
        }};
    }

    @Test( expected = FixtureCircularDependencyException.class )
    public void fixtureManagerFileWithReflectionDependenciesTest() throws Exception {
        FixtureManager manager = FixtureManager
            .builder()
            .withFile()
            .build();

        manager.loadFixtures( getFixturesWithReflectionDependencies() );
    }

    private Set<Class<? extends Fixture>> getFixturesWithReflectionDependencies() {
        return new HashSet<Class<? extends Fixture>>() {{
            add( FileFixture1.class );
            add( FileFixture8.class );
        }};
    }

    @After
    public void cleanup() throws IOException {
        for( int i = 1; i <= 8; ++i ) {
            Files.deleteIfExists( Paths.get( "/tmp/jfUnit/test/file" + i + ".txt" ) );
        }
    }

}
