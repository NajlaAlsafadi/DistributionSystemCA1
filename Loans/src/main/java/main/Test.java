package main;

import java.util.ArrayList;
import java.util.List;

import dao.CustomerDAO;
import dao.DepositDAO;
import dao.LoanDAO;
import entities.Customer;
import entities.Deposit;
import entities.Loan;

public class Test {
	
	//Used Before Adding rest to input into DB
	public Test() {
		CustomerDAO cDAO = new CustomerDAO();
		LoanDAO lDAO = new LoanDAO();
		DepositDAO dDAO = new DepositDAO();
		
		
		Deposit d1 = new Deposit("22/11/2022", 100.00);
		Deposit d2 = new Deposit("12/01/2022",20.00);
		Deposit d3 = new Deposit("24/06/2022",10.00);
		dDAO.persist(d1);
		dDAO.persist(d2);
		dDAO.persist(d3);
		
		List<Deposit> deposits = new ArrayList<Deposit>();
		deposits.add(d1);
		deposits.add(d2);
		deposits.add(d3);
		
		Loan loan = new Loan("House Loan", 300.00, 300.00, deposits);
		lDAO.persist(loan);
		
		
		Customer customer = new Customer("Naj","0894468775","Dublin 11" , 40000.00, loan );
		cDAO.persist(customer);
		
		
		ArrayList<Customer> customers = (ArrayList<Customer>) cDAO.getAllCustomers();
		for(Customer c : customers) {
			System.out.println("customer's name is "+c.getName());
			System.out.println("customer's Loan is "+ c.getLoan().getDescription());
			
			
			System.out.println("customer's Loan deposits are "+c.getLoan().getDeposits().get(0).getDepositDate());
			
		}
		
		
		customer.setName("Najla");
		cDAO.merge(customer);	
		
		
		//dDAO.remove(d3);
		
		
		System.out.println(cDAO.getCustomerByName("Najla").getPhone());
		
		
	}
	
	public static void main(String[] args) {
		new Test();
	}

}
