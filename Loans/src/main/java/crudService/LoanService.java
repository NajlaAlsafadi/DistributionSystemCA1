package crudService;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.CustomerDAO;
import dao.LoanDAO;
import entities.Customer;
import entities.Loan;

import java.util.List;

@Path("/loans")
public class LoanService {
    private LoanDAO dao = new LoanDAO();
    private CustomerDAO customerDao = new CustomerDAO();

    @GET
    @Path("/json")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Loan> getAllLoans() {
        return dao.getAllLoans();
    }
    
    @GET
    @Path("/xml")
    @Produces({MediaType.APPLICATION_XML})
    public List<Loan> getAllLoans1() {
        return dao.getAllLoans();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLoan(@PathParam("id") int id) {
        Loan loan = dao.getLoanById(id);
        if (loan != null)
            return Response.ok(loan).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }


    @POST
    @Path("/{customerId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addLoan(@PathParam("customerId") int customerId, Loan loan) {
        Customer customer = customerDao.getCustomerById(customerId); 
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); 
        }
        dao.persist(loan);
        customer.setLoan(loan); 
        customerDao.merge(customer); 
        

        return Response.ok(loan).build();
    }


    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateLoan(@PathParam("id") int id, Loan loan) {
        Loan existingLoan = dao.getLoanById(id);
        if (existingLoan == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        
        loan.setId(id);
        dao.merge(loan);
        return Response.ok(loan).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLoan(@PathParam("id") int id) {
        Loan loan = dao.getLoanById(id);
        if (loan != null) {
        	Customer customer = customerDao.getCustomerByLoanId(loan.getId());
            customer.setLoan(null);
            customerDao.merge(customer); 
            dao.remove(loan);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
