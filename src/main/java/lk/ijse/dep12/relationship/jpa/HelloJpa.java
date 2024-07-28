package lk.ijse.dep12.relationship.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.relationship.jpa.util.JpaUtil;

public class HelloJpa {
    public static void main(String[] args) {
       try( EntityManagerFactory emf= JpaUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager()){
           EntityTransaction tx = em.getTransaction();
           try{
               tx.begin();

               System.out.println(em);

               tx.commit();
           }catch (Throwable e){
               e.printStackTrace();
               tx.rollback();
           }
       }
    }
}
