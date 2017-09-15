package ru.weierstrass.fixture.readable;

import ru.weierstrass.reader.ReadableFromFile;

public class ReadableFromFileFixture1 implements ReadableFromFile {

    @Override
    public String getFileFromPath() {
        return getClass().getClassLoader().getResource( "data/test.json" ).getPath();
    }

}
