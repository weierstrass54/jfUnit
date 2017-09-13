package ru.weierstrass.fixture;

import java.util.HashSet;
import java.util.Set;

public interface Fixture {

    default Set<Class<? extends Fixture>> getDependencies() {
        return new HashSet<>();
    }

}
