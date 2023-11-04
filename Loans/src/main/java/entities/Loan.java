package entities;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import dao.LoanDAO;

@XmlRootElement(name = "loan")
@Entity
public class Loan {
	//private LoanDAO loanDao = new LoanDAO();

		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String description;
	private Double loanAmount;
	private Double totalLoanAmount;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "loan_id")
	
	private List<Deposit> deposits = new ArrayList<Deposit>();
	
	public Loan() {

	}

	
	public Loan(String description,Double loanAmount,Double totalLoanAmount, List<Deposit> deposits) {
		super();
		this.description = description;
		this.loanAmount = loanAmount;
		this.totalLoanAmount = totalLoanAmount;
		this.deposits = deposits;
	}
	
	
	public Loan(String description) {
		this.description = description;
	}

	public void addDeposit(Deposit deposit) {
		deposits.add(deposit);
	}
	
	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@XmlElement
	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	
	@XmlElement
	public List<Deposit> getDeposits() {
		return deposits;
	}



	public void setDeposits(List<Deposit> deposits) {
		this.deposits = deposits;
	}
	
	
	@XmlElement
	public Double getLoanAmount() {
		return loanAmount;
	}


	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	
	@XmlElement
	public Double getTotalLoanAmount() {
		return totalLoanAmount;
	}

	
	
	public void setTotalLoanAmount(Double totalLoanAmount) {
		this.totalLoanAmount = totalLoanAmount;
	}

	
	

}