package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Deposit;

public class DepositDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("mydb"); 	
	
	public DepositDAO() {
		
	}
	
	public void persist(Deposit deposit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(deposit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Deposit deposit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(deposit));
		em.getTransaction().commit();
		em.close();
	}
	
	public Deposit merge(Deposit deposit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Deposit updatedDeposit = em.merge(deposit);
		em.getTransaction().commit();
		em.close();
		return updatedDeposit;
	}
	
    public List<Deposit> findAll() {
    	EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT d FROM Deposit d", Deposit.class).getResultList();
    }
	


}
