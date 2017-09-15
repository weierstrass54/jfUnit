package ru.weierstrass.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface ReadableFromFile extends Readable<byte[]> {

    String getFileFromPath();

    @Override
    default byte[] read() throws IOException {
        return Files.readAllBytes( Paths.get( getFileFromPath() ) );
    }

}
