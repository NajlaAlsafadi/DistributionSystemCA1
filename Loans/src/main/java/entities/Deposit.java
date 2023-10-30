package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Deposit {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String depositDate;
    private double amount;
	
	public Deposit() {

	}

	public Deposit(String depositDate, Double amount) {
		this.depositDate = depositDate;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}

    @Override
    public String toString() {
        return "Deposit [depositDate=" + depositDate + ", amount=" + amount + "]";
    }
}


	
	
	
