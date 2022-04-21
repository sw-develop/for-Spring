package hellojpa;

import hellojpa.domain.item.Book;
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
      Book newBook = new Book();

      entityManager.persist(newBook);

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
