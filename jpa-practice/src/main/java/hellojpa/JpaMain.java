package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
  public static void main(String[] args){

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();

    try{
      Member member = new Member();

      entityManager.persist(member);

      transaction.commit();
    } catch (Exception e) {

      System.out.println(e.getMessage());

      transaction.rollback();
    } finally {
      entityManager.close();
    }

    entityManagerFactory.close();
  }
}
