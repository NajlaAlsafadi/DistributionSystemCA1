package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import entities.Deposit;
import entities.Loan;

public class LoanDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("mydb"); 	
	
	public LoanDAO() {
		
	}
	
	public void persist(Loan loan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(loan);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Loan loan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(loan));
		em.getTransaction().commit();
		em.close();
	}
	
	public Loan merge(Loan loan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Loan updatedLoan = em.merge(loan);
		em.getTransaction().commit();
		em.close();
		return updatedLoan;
	}
	
    public List<Loan> getAllLoans() {
    	EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();
    }

    public Loan getLoanById(int id) {
        EntityManager em = emf.createEntityManager();
        Loan loan = null;
        try {
            loan = em.find(Loan.class, id);
        } finally {
            em.close();
        }
        return loan;
    }
    
    public Loan getLoanByDeposit(Deposit deposit) {
        EntityManager em = emf.createEntityManager();
        Loan loan = null;
        try {
            loan = em.createQuery("SELECT l FROM Loan l JOIN l.deposits d WHERE d = :deposit", Loan.class)
                     .setParameter("deposit", deposit)
                     .getSingleResult();
        } catch (NoResultException e) {
       
        } finally {
            em.close();
        }
        return loan;
    }
    
}

