package org.a4j.presentation.ultrafast;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/team")
@ApplicationScoped
public class GalaxyResource {

    @Inject
    private TeamService service;

    @GET
    public Iterable<Player> getPlayer() {
        return this.service.getPlayers();
    }

    @PUT
    public void add(Player player){
        this.service.add(player);
    }

    @GET
    @Path("{name}")
    public Player findBy(@PathParam("name") String name) {
        return this.service.findById(name)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }


}
