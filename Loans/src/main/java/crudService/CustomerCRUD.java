package crudService;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.CustomerDAO;
import entities.Customer;

@Path("/customers")
public class CustomerCRUD {
	
	private CustomerDAO customerDAO = new CustomerDAO();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
	
	@GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerByName(@PathParam("name") String name) {
        return customerDAO.getCustomerByName(name);
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void addCustomer(Customer customer) {
        customerDAO.persist(customer);
    }
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomer(@PathParam("id") int id, Customer customer) {
		Customer existingCustomer = customerDAO.getCustomerByName(customer.getName());
		if (existingCustomer.getId() == id) {
			customerDAO.merge(customer);
		}
	}
	
    @DELETE
    @Path("/delete/{customerName}")
    public String deleteCustomer(@PathParam("customerName") String customerName) {
        CustomerDAO dao = new CustomerDAO();
        Customer customer = dao.getCustomerByName(customerName);
        dao.remove(customer);
        return "Customer " + customer.getName() + " deleted";
    }
}
