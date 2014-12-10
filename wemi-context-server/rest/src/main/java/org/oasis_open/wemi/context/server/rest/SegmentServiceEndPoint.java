package org.oasis_open.wemi.context.server.rest;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.oasis_open.wemi.context.server.api.Metadata;
import org.oasis_open.wemi.context.server.api.PartialList;
import org.oasis_open.wemi.context.server.api.segments.Segment;
import org.oasis_open.wemi.context.server.api.User;
import org.oasis_open.wemi.context.server.api.services.SegmentService;
import org.oasis_open.wemi.context.server.api.services.SegmentsAndScores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by loom on 26.04.14.
 */
@WebService
@Produces(MediaType.APPLICATION_JSON)
@CrossOriginResourceSharing(
        allowAllOrigins = true,
        allowCredentials = true
)
public class SegmentServiceEndPoint implements SegmentService {

    SegmentService segmentService;

    public SegmentServiceEndPoint() {
        System.out.println("Initializing segment service endpoint...");
    }

    @WebMethod(exclude=true)
    public void setSegmentService(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @GET
    @Path("/{segmentID}/match")
    public PartialList<User> getMatchingIndividuals(@PathParam("segmentID") String segmentId, @QueryParam("offset") @DefaultValue("0") int offset, @QueryParam("size") @DefaultValue("50") int size, @QueryParam("sort") String sortBy) {
        return segmentService.getMatchingIndividuals(segmentId, offset, size, sortBy);
    }

    @GET
    @Path("/{segmentID}/count")
    public long getMatchingIndividualsCount(@PathParam("segmentID") String segmentId) {
        return segmentService.getMatchingIndividualsCount(segmentId);
    }

    @GET
    @Path("/{segmentID}/match/{user}")
    public Boolean isUserInSegment(@PathParam("user") User user, @PathParam("segmentID") String segmentId) {
        return segmentService.isUserInSegment(user, segmentId);
    }

    @GET
    @Path("/match/{userID}")
    public SegmentsAndScores getSegmentsAndScoresForUser(@PathParam("userID") User user) {
        return segmentService.getSegmentsAndScoresForUser(user);
    }

    @GET
    @Path("/")
    public Set<Metadata> getSegmentMetadatas() {
        return segmentService.getSegmentMetadatas();
    }

    @GET
    @Path("/{segmentID}")
    public Segment getSegmentDefinition(@PathParam("segmentID") String segmentId) {
        return segmentService.getSegmentDefinition(segmentId);
    }

    @POST
    @Path("/{segmentID}")
    public void setSegmentDefinition(@PathParam("segmentID") String segmentId, Segment segment) {
        segmentService.setSegmentDefinition(segmentId, segment);
    }

    @PUT
    @Path("/{segmentID}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createSegmentDefinition(@PathParam("segmentID") String segmentId, @FormParam("segmentName") String segmentName, @FormParam("segmentDescription") String segmentDescription) {
        segmentService.createSegmentDefinition(segmentId, segmentName, segmentDescription);
    }

    @DELETE
    @Path("/{segmentID}")
    public void removeSegmentDefinition(@PathParam("segmentID") String segmentId) {
        segmentService.removeSegmentDefinition(segmentId);
    }

}
