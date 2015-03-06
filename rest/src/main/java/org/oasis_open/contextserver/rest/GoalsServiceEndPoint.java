package org.oasis_open.contextserver.rest;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.oasis_open.contextserver.api.Metadata;
import org.oasis_open.contextserver.api.goals.Goal;
import org.oasis_open.contextserver.api.goals.GoalReport;
import org.oasis_open.contextserver.api.query.AggregateQuery;
import org.oasis_open.contextserver.api.services.GoalsService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@WebService
@Produces(MediaType.APPLICATION_JSON)
@CrossOriginResourceSharing(
        allowAllOrigins = true,
        allowCredentials = true
)
public class GoalsServiceEndPoint {

    private GoalsService goalsService;

    @WebMethod(exclude = true)
    public void setGoalsService(GoalsService goalsService) {
        this.goalsService = goalsService;
    }

    @GET
    @Path("/")
    public Set<Metadata> getGoalMetadatas() {
        return goalsService.getGoalMetadatas();
    }

    @POST
    @Path("/")
    public void setGoal(Goal goal) {
        goalsService.setGoal(goal);
    }

    @GET
    @Path("/{scope}/")
    public Set<Metadata> getGoalMetadatas(@PathParam("scope") String scope) {
        return goalsService.getGoalMetadatas(scope);
    }

    @GET
    @Path("/{campaignId}/campaign")
    public Set<Metadata> getCampaignGoalMetadatas(@PathParam("campaignId") String campaignId) {
        return goalsService.getCampaignGoalMetadatas(campaignId);
    }

    @GET
    @Path("/{scope}/{goalId}")
    public Goal getGoal(@PathParam("scope") String scope, @PathParam("goalId") String goalId) {
        return goalsService.getGoal(scope, goalId);
    }

    @DELETE
    @Path("/{scope}/{goalId}")
    public void removeGoal(@PathParam("scope") String scope, @PathParam("goalId") String goalId) {
        goalsService.removeGoal(scope, goalId);
    }

    @GET
    @Path("/{scope}/{goalID}/report")
    public GoalReport getGoalReport(@PathParam("scope") String scope, @PathParam("goalID") String goalId) {
        return goalsService.getGoalReport(scope, goalId);
    }

    @POST
    @Path("/{scope}/{goalID}/report")
    public GoalReport getGoalReport(@PathParam("scope") String scope, @PathParam("goalID") String goalId, AggregateQuery query) {
        return goalsService.getGoalReport(scope, goalId, query);
    }

}
