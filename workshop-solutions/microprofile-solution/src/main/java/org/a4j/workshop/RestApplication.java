package org.a4j.workshop;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 *
 */
@ApplicationPath("/")
@ApplicationScoped
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(HelloController.class, TeamResource.class);
    }
}
