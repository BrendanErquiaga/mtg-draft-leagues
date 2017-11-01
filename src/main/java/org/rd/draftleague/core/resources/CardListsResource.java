package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.CardListDAO;
import org.rd.draftleague.core.model.CardList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @POST
    @UnitOfWork
    public CardList createCardList(CardList cardList) {
        return cardListDAO.create(cardList);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public CardList update(@PathParam("id") LongParam id, CardList cardList) {
        return cardListDAO.update(id.get(), cardList)
                .orElseThrow(() ->
                        new WebApplicationException("League not found", 404));
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response delete(@PathParam("id") LongParam id) {
        Optional<CardList> cardList = findById(id);

        if(cardList.isPresent()) {
            cardListDAO.delete(cardList.get());
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
