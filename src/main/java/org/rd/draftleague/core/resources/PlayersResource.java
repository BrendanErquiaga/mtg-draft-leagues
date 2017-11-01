package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.PlayerDAO;
import org.rd.draftleague.core.model.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/players")
@Produces(MediaType.APPLICATION_JSON)
public class PlayersResource {

    private PlayerDAO playerDAO;

    public PlayersResource(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @GET
    @UnitOfWork
    public List<Player> findByName(@QueryParam("name") Optional<String> name) {
        if(name.isPresent()) {
            return playerDAO.findByName(name.get());
        } else {
            return playerDAO.findAll();
        }
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Player> findById(@PathParam("id") LongParam id) {
        return playerDAO.findById(id.get());
    }

    @POST
    @UnitOfWork
    public Player createPlayer(Player player) {
        return playerDAO.create(player);
    }
}
