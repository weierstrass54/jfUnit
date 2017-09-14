package ru.weierstrass.fixture.file;

import ru.weierstrass.file.FileFixture;
import ru.weierstrass.fixture.Fixture;

import java.util.HashSet;
import java.util.Set;

public class FileFixture6 implements FileFixture {

    @Override
    public String getFilePath() {
        return "/tmp/jfUnit/test/file6.txt";
    }

    @Override
    public String getData() {
        return "Recursive dependency 2";
    }

    @Override
    public Set<Class<? extends Fixture>> getDependencies() {
        return new HashSet<Class<? extends Fixture>>() {{
            add( FileFixture7.class );
        }};
    }

}