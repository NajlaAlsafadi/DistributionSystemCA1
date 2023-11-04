package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Customer;

public class CustomerDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("mydb"); 	
	
	public CustomerDAO() {
		
	}
	
	public void persist(Customer customer) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Customer customer) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(customer));
		em.getTransaction().commit();
		em.close();
	}
	
	public Customer merge(Customer customer) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Customer updatedCustomer = em.merge(customer);
		em.getTransaction().commit();
		em.close();
		return updatedCustomer;
	}
	
	
	public List<Customer> getAllCustomers() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Customer> customersFromDB = new ArrayList<Customer>();
		customersFromDB = em.createNamedQuery("Customer.findAll").getResultList();
		em.getTransaction().commit();
		em.close();
		return customersFromDB;
	}
	
	public Customer getCustomerByName(String name){
		EntityManager em = emf.createEntityManager();
		List<Customer> customers = (List<Customer>) 
				em.createNamedQuery("Customer.findByName").
				setParameter("name", name).getResultList();
		em.close();

		Customer cus = new Customer();
		for(Customer c: customers) {
			cus = c;
		}
		return cus;
	}
	
	public Customer getCustomerById(int id) {
	    EntityManager em = emf.createEntityManager();
	    Customer customer = em.find(Customer.class, id);
	    em.close();
	    return customer;
	}
	
	public Customer getCustomerByLoanId(int loanId) {
	    for (Customer customer : getAllCustomers()) {
	        if (customer.getLoan() != null && customer.getLoan().getId() == loanId) {
	            return customer;
	        }
	    }
	    return null;
	}
}
