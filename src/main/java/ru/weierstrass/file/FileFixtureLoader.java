package ru.weierstrass.file;

import lombok.extern.slf4j.Slf4j;
import ru.weierstrass.fixture.FixtureAlreadyLoadedException;
import ru.weierstrass.fixture.FixtureLoader;
import ru.weierstrass.fixture.FixtureLoadingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class FileFixtureLoader implements FixtureLoader<FileFixture> {

    private final Set<String> loadedFiles = new HashSet<>();

    @Override
    public void load( FileFixture fixture ) throws FixtureLoadingException {
        if( loadedFiles.contains( fixture.getFilePath() ) ) {
            throw new FixtureAlreadyLoadedException( "Файл " + fixture.getFilePath() + " уже загружен." );
        }
        try {
            write( fixture );
            loadedFiles.add( fixture.getFilePath() );
        }
        catch( Exception e ) {
            throw new FixtureLoadingException( e );
        }
    }

    private void write( FileFixture fixture ) throws IOException {
        Path path = Paths.get( fixture.getFilePath() );
        createDirectoryIfNotExists( path.getParent() );
        Files.write(
            path,
            fixture.getData().getBytes(),
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    private void createDirectoryIfNotExists( Path path ) throws IOException {
        if( !Files.exists( path ) ) {
            Files.createDirectories( path );
        }
    }

}
