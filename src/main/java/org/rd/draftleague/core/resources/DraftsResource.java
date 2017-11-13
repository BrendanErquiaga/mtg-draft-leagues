package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.DraftDAO;
import org.rd.draftleague.core.model.Draft;
import org.rd.draftleague.core.model.League;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/drafts")
@Produces(MediaType.APPLICATION_JSON)
public class DraftsResource {
    private DraftDAO draftDAO;

    public DraftsResource(DraftDAO draftDAO) { this.draftDAO = draftDAO; }

    @GET
    @UnitOfWork
    public Optional<List<Draft>> findAll() {
        return draftDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Draft> findById(@PathParam("id")LongParam id) {
        return draftDAO.findById(id.get());
    }

    @POST
    @UnitOfWork
    public Draft createDraft(Draft draft) {
        return draftDAO.create(draft);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Draft update(@PathParam("id") LongParam id, Draft draft) {
        return draftDAO.update(id.get(), draft)
                .orElseThrow(() ->
                    new WebApplicationException("Draft not found", 404));
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response delete(@PathParam("id") LongParam id) {
        Optional<Draft> draft = findById(id);

        if(draft.isPresent()) {
            draftDAO.delete(draft.get());
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/join-league")
    @UnitOfWork
    public Draft joinLeague(@PathParam("id") LongParam draftId, League league) {
        Optional<Draft> existingDraftOptional = draftDAO.findById(draftId.get());

        if(existingDraftOptional.isPresent()) {
            Draft existingDraft = existingDraftOptional.get();
            existingDraft.joinLeauge(league);
            return update(draftId, existingDraft);
        } else {
            throw new WebApplicationException("Draft not found", 404);
        }
    }
}
