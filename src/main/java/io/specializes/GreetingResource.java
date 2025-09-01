package io.specializes;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    @Inject private Strategy strategy;
//    @Inject private PrioStrategy strategy;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return strategy.greet();
    }
}
