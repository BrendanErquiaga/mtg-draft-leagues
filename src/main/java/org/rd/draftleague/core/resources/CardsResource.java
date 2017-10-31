package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.CardDAO;
import org.rd.draftleague.core.model.Card;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
public class CardsResource {
    private CardDAO cardDAO;

    public CardsResource(CardDAO draftDAO) { this.cardDAO = draftDAO; }

    @GET
    @UnitOfWork
    public Optional<List<Card>> findAll() {
        return cardDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Card> findById(@PathParam("id")LongParam id) {
        return cardDAO.findById(id.get());
    }
}
