package crudService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.DepositDAO;
import dao.LoanDAO;
import entities.Customer;
import entities.Deposit;
import entities.Loan;

import java.util.List;

@Path("/deposits")
public class DepositService {
    private DepositDAO dao = new DepositDAO();
    private LoanDAO loanDao = new LoanDAO();

    @GET
    @Path("/json")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Deposit> getAllDeposits() {
        return dao.getAllDeposits();
    }
    @GET
    @Path("/xml")
    @Produces({MediaType.APPLICATION_XML})
    public List<Deposit> getAllDeposits1() {
        return dao.getAllDeposits();
    }
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getDeposit(@PathParam("id") int id) {
        Deposit deposit = dao.getDepositById(id);
        if (deposit != null)
            return Response.ok(deposit).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    
    @POST
    @Path("/{loanId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addDeposit(@PathParam("loanId") int loanId, Deposit deposit) {
        Loan loan = loanDao.getLoanById(loanId);
        if (loan != null) {
            double newLoanAmount = loan.getLoanAmount() - deposit.getAmount();
            if (newLoanAmount < 0) newLoanAmount = 0;
            loan.setLoanAmount(newLoanAmount);
          
            loan.getDeposits().add(deposit);
            
            loanDao.merge(loan);
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(deposit).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})
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
           
            Loan loan = loanDao.getLoanByDeposit(deposit); 
            if (loan != null) {
                loan.getDeposits().remove(deposit);
                double newLoanAmount = loan.getLoanAmount() + deposit.getAmount();
                loan.setLoanAmount(newLoanAmount);
                loanDao.merge(loan);
                dao.remove(deposit);
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("loan not found").build();
            }

            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Deposit not found").build();
        }
    }
}
