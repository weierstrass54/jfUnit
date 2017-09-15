package ru.weierstrass.fixture.loadable;

import ru.weierstrass.loader.LoadableToFile;

public class LoadableToFile1 implements LoadableToFile {

    @Override
    public String getFileToPath() {
        return "/tmp/jfUnit/test/file1.txt";
    }

    @Override
    public String getData() {
        return "Hello, world!";
    }

}
