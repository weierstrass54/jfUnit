package ru.weierstrass.fixture.file;

import ru.weierstrass.file.FileFixture;
import ru.weierstrass.fixture.Fixture;

import java.util.HashSet;
import java.util.Set;

public class FileFixture7 implements FileFixture {

    @Override
    public String getFilePath() {
        return "/tmp/jfUnit/test/file7.txt";
    }

    @Override
    public String getData() {
        return "Recursive dependency 3";
    }

    @Override
    public Set<Class<? extends Fixture>> getDependencies() {
        return new HashSet<Class<? extends Fixture>>() {{
            add( FileFixture5.class );
        }};
    }

}