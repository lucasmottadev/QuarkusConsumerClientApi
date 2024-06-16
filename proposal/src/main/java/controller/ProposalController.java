package controller;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.hibernate.service.spi.InjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/api/proposal")
public class ProposalController {

    public Logger createLogger(){
        return LoggerFactory.getLogger(ProposalController.class);
    }

    public final Logger LOG = createLogger();

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    ProposalService proposalService;

    @GET
    @javax.ws.rs.Path("/{id}")
    @RolesAllowed({"user","manager"})
    public ProposalDetailsDTO findDetailsProposal(@PathParam("id") long id){
        return proposalService.findFullProposal(id);
    }

    @POST
    @RolesAllowed("proposal-customer")
    public Response createProposal(ProposalDetailsDTO proposalDetails){

        LOG.info("--- Recebendo Proposta de Compra ---");

        try {

            proposalService.createNewProposal(proposalDetails);
            return Response.ok().build();

        } catch (Exception e) {

            return Response.serverError().build();

        }

    }

    @DELETE
    @javax.ws.rs.Path("/{id}")
    @RolesAllowed("manager")
    public Response removeProposal(@PathParam("id") long id){

        try {
            proposalService.removeProposal(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }


}
