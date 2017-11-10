package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.DraftedCardDAO;
import org.rd.draftleague.core.model.DraftedCard;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/drafted-cards")
@Produces(MediaType.APPLICATION_JSON)
public class DraftedCardsResource {
    private DraftedCardDAO draftedCardDAO;
    
    public DraftedCardsResource(DraftedCardDAO draftedCardDAO) { this.draftedCardDAO = draftedCardDAO; }

    @GET
    @UnitOfWork
    public Optional<List<DraftedCard>> findAll() {
        return draftedCardDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<DraftedCard> findById(@PathParam("id")LongParam id) {
        return draftedCardDAO.findById(id.get());
    }

    @POST
    @UnitOfWork
    public DraftedCard createCard(DraftedCard draftedCard) {
        return draftedCardDAO.create(draftedCard);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public DraftedCard update(@PathParam("id") LongParam id, DraftedCard draftedCard) {
        return draftedCardDAO.update(id.get(), draftedCard)
                .orElseThrow(() ->
                        new WebApplicationException("League not found", 404));
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response delete(@PathParam("id") LongParam id) {
        Optional<DraftedCard> draftedCard = findById(id);

        if(draftedCard.isPresent()) {
            draftedCardDAO.delete(draftedCard.get());
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
