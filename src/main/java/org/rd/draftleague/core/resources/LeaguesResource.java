package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.LeagueDAO;
import org.rd.draftleague.core.model.League;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
}
