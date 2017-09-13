package ru.weierstrass.file;

import ru.weierstrass.fixture.Fixture;

public interface FileFixture extends Fixture {

    String getFilePath();

    String getData();

}
