package entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="select o from Customer o"), 
	@NamedQuery(name = "Customer.findByName", query = "select o from Customer o where o.name=:name")
})

@Entity
public class Customer {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String phone;
	private String address;
	private Double annualSalary;
	
	@OneToOne
	private Loan loan;

	public Customer() {

	}

	public Customer(String name,String phone,String address, Double annualSalary, Loan loan) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.annualSalary = annualSalary;
		this.loan = loan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Double getAnnualSalary() {
		return annualSalary;
	}



	public void setAnnualSalary(Double annualSalary) {
		this.annualSalary = annualSalary;
	}


	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	

}

