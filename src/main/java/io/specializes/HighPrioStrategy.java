package io.specializes;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@Alternative
@Priority(2)
@ApplicationScoped
public class HighPrioStrategy extends LowPrioStrategy {
    @Override
    public String greet() {
        return "Hello from HighPrioStrategy";
    }
}
