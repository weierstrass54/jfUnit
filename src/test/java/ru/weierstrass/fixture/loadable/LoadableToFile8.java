package ru.weierstrass.fixture.loadable;

import ru.weierstrass.loader.LoadableToFile;
import ru.weierstrass.fixture.Fixture;

import java.util.HashSet;
import java.util.Set;

public class LoadableToFile8 implements LoadableToFile {

    @Override
    public String getFileToPath() {
        return "/tmp/jfUnit/test/file8.txt";
    }

    @Override
    public String getData() {
        return "Reflection dependency";
    }

    @Override
    public Set<Class<? extends Fixture>> getDependencies() {
        return new HashSet<Class<? extends Fixture>>() {{
            add( LoadableToFile8.class );
        }};
    }

}
