package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.PlayerDAO;
import org.rd.draftleague.core.model.Draft;
import org.rd.draftleague.core.model.League;
import org.rd.draftleague.core.model.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Player update(@PathParam("id") LongParam id, Player player) {
        return playerDAO.update(id.get(), player)
                .orElseThrow(() ->
                        new WebApplicationException("Player not found", 404));
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response delete(@PathParam("id") LongParam id) {
        Optional<Player> player = findById(id);

        if(player.isPresent()) {
            playerDAO.delete(player.get());
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/join-league")
    @UnitOfWork
    public Player joinLeague(@PathParam("id") LongParam playerId, League league) {
        Optional<Player>  existingPlayerOptional = playerDAO.findById(playerId.get());

        if(existingPlayerOptional.isPresent()) {
            Player existingPlayer = existingPlayerOptional.get();
            existingPlayer.joinLeague(league);
            return update(playerId, existingPlayer);
        } else {
            throw new WebApplicationException("Player not found", 404);
        }
    }

    @POST
    @Path("/{id}/join-draft")
    @UnitOfWork
    public Player joinDraft(@PathParam("id") LongParam playerId, Draft draft) {
        Optional<Player>  existingPlayerOptional = playerDAO.findById(playerId.get());

        if(existingPlayerOptional.isPresent()) {
            Player existingPlayer = existingPlayerOptional.get();
            existingPlayer.joinDraft(draft);
            return update(playerId, existingPlayer);
        } else {
            throw new WebApplicationException("Player not found", 404);
        }
    }
}
