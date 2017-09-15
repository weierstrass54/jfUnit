package ru.weierstrass.fixture.loadable;

import ru.weierstrass.loader.LoadableToFile;

public class LoadableToFile4 implements LoadableToFile {

    @Override
    public String getFileToPath() {
        return "/tmp/jfUnit/test/file4.txt";
    }

    @Override
    public String getData() {
        return "I hate you, world!";
    }

}
