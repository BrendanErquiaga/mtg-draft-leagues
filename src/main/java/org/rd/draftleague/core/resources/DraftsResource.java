package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.DraftDAO;
import org.rd.draftleague.core.model.Draft;
import org.rd.draftleague.core.model.League;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
}
