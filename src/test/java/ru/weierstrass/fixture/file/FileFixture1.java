package ru.weierstrass.fixture.file;

import ru.weierstrass.file.FileFixture;

public class FileFixture1 implements FileFixture {

    @Override
    public String getFilePath() {
        return "/tmp/jfUnit/test/file1.txt";
    }

    @Override
    public String getData() {
        return "Hello, world!";
    }

}
