package io.specializes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class SpecializedStrategy extends DefaultStrategy {
    @Override
    public String greet() {
        return "Hello from SpecializedStrategy";
    }
}
