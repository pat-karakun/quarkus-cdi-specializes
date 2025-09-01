package io.specializes;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@Alternative
@Priority(1)
@ApplicationScoped
public class LowPrioStrategy implements PrioStrategy {
    @Override
    public String greet() {
        return "Hello from LowPrioStrategy";
    }
}
