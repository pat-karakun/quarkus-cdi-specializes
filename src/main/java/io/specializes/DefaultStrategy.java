package io.specializes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;

@Default
@ApplicationScoped
public class DefaultStrategy implements Strategy {
    @Override
    public String greet() {
        return "Hello from DefaultStrategy";
    }
}
