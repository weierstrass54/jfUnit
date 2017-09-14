package ru.weierstrass.fixture.file;

import ru.weierstrass.file.FileFixture;

public class FileFixture4 implements FileFixture {

    @Override
    public String getFilePath() {
        return "/tmp/jfUnit/test/file4.txt";
    }

    @Override
    public String getData() {
        return "I hate you, world!";
    }

}
