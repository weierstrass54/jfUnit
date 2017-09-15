package ru.weierstrass.fixture.file;

import ru.weierstrass.loader.LoadableToFile;

public class LoadableToFile2 implements LoadableToFile {

    @Override
    public String getFilePath() {
        return "/tmp/jfUnit/test/file2.txt";
    }

    @Override
    public String getData() {
        return "Goodbye, world!";
    }

}
