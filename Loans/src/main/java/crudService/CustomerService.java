package crudService;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.CustomerDAO;
import entities.Customer;
import entities.HelloWorld;

import java.util.List;

@Path("/customers")
public class CustomerService {
    private CustomerDAO dao = new CustomerDAO();
    
    //Test hello path just to make clarify if the problem is my method or the server

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello(){
        return "Hello World";    
    }
    
    @POST
    @Path("/helloxml")
    @Consumes({MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    public HelloWorld helloXml(HelloWorld helloWorld) {
        System.out.println("Received message: " + helloWorld.getMessage());
        return new HelloWorld("Hello, " + helloWorld.getMessage());
    }

    @GET
    @Path("/json")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Customer> getAllCustomers() {
        return dao.getAllCustomers();
    }
    
    @GET
    @Path("/xml")
    @Produces({MediaType.APPLICATION_XML})
    public List<Customer> getAllCustomers1() {
        return dao.getAllCustomers();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCustomer(@PathParam("id") int id) {
        Customer customer = dao.getCustomerById(id);
        if (customer != null)
            return Response.ok(customer).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addCustomer(Customer customer) {
        dao.persist(customer);
        return Response.ok(customer).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        Customer existingCustomer = dao.getCustomerById(id);
        if (existingCustomer == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        
        customer.setId(id);
        dao.merge(customer);
        return Response.ok(customer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        Customer customer = dao.getCustomerById(id);
        if (customer != null) {
            dao.remove(customer);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}