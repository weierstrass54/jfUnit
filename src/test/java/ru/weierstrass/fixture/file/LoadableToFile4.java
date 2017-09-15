package ru.weierstrass.fixture.file;

import ru.weierstrass.loader.LoadableToFile;

public class LoadableToFile4 implements LoadableToFile {

    @Override
    public String getFilePath() {
        return "/tmp/jfUnit/test/file4.txt";
    }

    @Override
    public String getData() {
        return "I hate you, world!";
    }

}
