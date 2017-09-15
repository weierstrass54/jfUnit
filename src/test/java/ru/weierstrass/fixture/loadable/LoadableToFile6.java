package ru.weierstrass.fixture.loadable;

import ru.weierstrass.loader.LoadableToFile;
import ru.weierstrass.fixture.Fixture;

import java.util.HashSet;
import java.util.Set;

public class LoadableToFile6 implements LoadableToFile {

    @Override
    public String getFileToPath() {
        return "/tmp/jfUnit/test/file6.txt";
    }

    @Override
    public String getData() {
        return "Recursive dependency 2";
    }

    @Override
    public Set<Class<? extends Fixture>> getDependencies() {
        return new HashSet<Class<? extends Fixture>>() {{
            add( LoadableToFile7.class );
        }};
    }

}