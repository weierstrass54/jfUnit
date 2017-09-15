package ru.weierstrass.fixture.file;

import ru.weierstrass.loader.LoadableToFile;

public class LoadableToFile1 implements LoadableToFile {

    @Override
    public String getFilePath() {
        return "/tmp/jfUnit/test/file1.txt";
    }

    @Override
    public String getData() {
        return "Hello, world!";
    }

}
