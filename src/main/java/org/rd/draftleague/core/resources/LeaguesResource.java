package org.rd.draftleague.core.resources;

import com.sun.media.jfxmedia.events.PlayerStateEvent;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.LeagueDAO;
import org.rd.draftleague.core.dao.PlayerDAO;
import org.rd.draftleague.core.model.League;
import org.rd.draftleague.core.model.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/leagues")
@Produces(MediaType.APPLICATION_JSON)
public class LeaguesResource {
    private LeagueDAO leagueDAO;

    public LeaguesResource(LeagueDAO leagueDAO) { this.leagueDAO = leagueDAO; }

    @GET
    @UnitOfWork
    public Optional<List<League>> findAll() {
        return leagueDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<League> findById(@PathParam("id")LongParam id) {
        return leagueDAO.findById(id.get());
    }

    @POST
    @UnitOfWork
    public League createLeague(League league) {
        return leagueDAO.create(league);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public League update(@PathParam("id") LongParam id, League league) {
        return leagueDAO.update(id.get(), league)
                .orElseThrow(() ->
                        new WebApplicationException("League not found", 404));
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response delete(@PathParam("id") LongParam id) {
        Optional<League> league = findById(id);

        if(league.isPresent()) {
            leagueDAO.delete(league.get());
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/addplayer")
    @UnitOfWork
    public League addPlayer(@PathParam("id") LongParam id, Player player) {
        return leagueDAO.addPlayer(id.get(), player)
                .orElseThrow(() ->
                        new WebApplicationException("League not found", 404));
    }
    //Doesn't save... why not
}
