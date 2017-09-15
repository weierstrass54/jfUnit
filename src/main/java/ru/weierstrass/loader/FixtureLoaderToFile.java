package ru.weierstrass.loader;

import lombok.extern.slf4j.Slf4j;
import ru.weierstrass.exception.FixtureAlreadyLoadedException;
import ru.weierstrass.exception.FixtureLoadingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class FixtureLoaderToFile implements FixtureLoader<LoadableToFile> {

    private final Set<String> loadedFiles = new HashSet<>();

    @Override
    public void load( LoadableToFile fixture ) throws FixtureLoadingException {
        if( loadedFiles.contains( fixture.getFileToPath() ) ) {
            throw new FixtureAlreadyLoadedException( "Файл " + fixture.getFileToPath() + " уже загружен." );
        }
        try {
            write( fixture );
            loadedFiles.add( fixture.getFileToPath() );
        }
        catch( Exception e ) {
            throw new FixtureLoadingException( e );
        }
    }

    private void write( LoadableToFile fixture ) throws IOException {
        Path path = Paths.get( fixture.getFileToPath() );
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
