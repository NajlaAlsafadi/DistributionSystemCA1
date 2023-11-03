package crudService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.DepositDAO;
import entities.Deposit;

import java.util.List;

@Path("/deposits")
public class DepositService {
    private DepositDAO dao = new DepositDAO();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Deposit> getAllDeposits() {
        return dao.getAllDeposits();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDeposit(@PathParam("id") int id) {
        Deposit deposit = dao.getDepositById(id);
        if (deposit != null)
            return Response.ok(deposit).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addDeposit(Deposit deposit) {
        dao.persist(deposit);
        return Response.ok(deposit).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateDeposit(@PathParam("id") int id, Deposit deposit) {
        Deposit existingDeposit = dao.getDepositById(id);
        if (existingDeposit == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        
        deposit.setId(id);
        dao.merge(deposit);
        return Response.ok(deposit).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDeposit(@PathParam("id") int id) {
        Deposit deposit = dao.getDepositById(id);
        if (deposit != null) {
            dao.remove(deposit);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
