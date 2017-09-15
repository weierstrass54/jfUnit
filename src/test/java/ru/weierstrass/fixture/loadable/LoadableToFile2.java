package ru.weierstrass.fixture.loadable;

import ru.weierstrass.loader.LoadableToFile;

public class LoadableToFile2 implements LoadableToFile {

    @Override
    public String getFileToPath() {
        return "/tmp/jfUnit/test/file2.txt";
    }

    @Override
    public String getData() {
        return "Goodbye, world!";
    }

}
