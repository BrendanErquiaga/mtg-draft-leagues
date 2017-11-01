package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.CardDAO;
import org.rd.draftleague.core.model.Card;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @POST
    @UnitOfWork
    public Card createCard(Card card) {
        return cardDAO.create(card);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Card update(@PathParam("id") LongParam id, Card card) {
        return cardDAO.update(id.get(), card)
                .orElseThrow(() ->
                        new WebApplicationException("League not found", 404));
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response delete(@PathParam("id") LongParam id) {
        Optional<Card> card = findById(id);

        if(card.isPresent()) {
            cardDAO.delete(card.get());
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
