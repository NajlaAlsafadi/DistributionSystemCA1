package entities;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="select o from Customer o"), 
	@NamedQuery(name = "Customer.findByName", query = "select o from Customer o where o.name=:name")
})
@XmlRootElement
@Entity
public class Customer {
		

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String phone;
	private String address;
	private Double annualSalary;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
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
	@JsonProperty
	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty
	@XmlElement
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	@XmlElement
	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty
	@XmlElement
	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty
	@XmlElement
	public Double getAnnualSalary() {
		return annualSalary;
	}



	public void setAnnualSalary(Double annualSalary) {
		this.annualSalary = annualSalary;
	}
	@JsonProperty
	@XmlElement
	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	

}

