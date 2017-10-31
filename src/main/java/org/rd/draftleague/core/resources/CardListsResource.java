package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.CardListDAO;
import org.rd.draftleague.core.model.CardList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/cardlists")
@Produces(MediaType.APPLICATION_JSON)
public class CardListsResource {

    private CardListDAO cardListDAO;

    public CardListsResource(CardListDAO draftDAO) { this.cardListDAO = draftDAO; }

    @GET
    @UnitOfWork
    public Optional<List<CardList>> findAll() {
        return cardListDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<CardList> findById(@PathParam("id")LongParam id) {
        return cardListDAO.findById(id.get());
    }
}
