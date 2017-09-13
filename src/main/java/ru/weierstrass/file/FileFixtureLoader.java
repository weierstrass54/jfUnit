package ru.weierstrass.file;

import ru.weierstrass.fixture.FixtureLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileFixtureLoader implements FixtureLoader<FileFixture> {

    @Override
    public void load( FileFixture fixture ) throws Exception {
        write( fixture );
    }

    private void write( FileFixture fixture ) throws IOException {
        Files.write( Paths.get( fixture.getFilePath() ), fixture.getData().getBytes() );
    }

}
