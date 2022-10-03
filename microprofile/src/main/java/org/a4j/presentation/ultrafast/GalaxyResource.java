package org.a4j.presentation.ultrafast;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/galaxy")
@ApplicationScoped
public class GalaxyResource {

    @Inject
    private GalaxyService service;



    @GET
    public Iterable<CelestialBody> getBodies() {
        return this.service.getBodies();
    }

    @PUT
    public void add(CelestialBody body){
        this.service.add(body);
    }

    @GET
    @Path("{name}")
    public CelestialBody findBy(@PathParam("name") String name) {
        return this.service.findById(name)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }


}
