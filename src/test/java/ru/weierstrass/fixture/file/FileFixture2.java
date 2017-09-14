package ru.weierstrass.fixture.file;

import ru.weierstrass.file.FileFixture;

public class FileFixture2 implements FileFixture {

    @Override
    public String getFilePath() {
        return "/tmp/jfUnit/test/file2.txt";
    }

    @Override
    public String getData() {
        return "Goodbye, world!";
    }

}
