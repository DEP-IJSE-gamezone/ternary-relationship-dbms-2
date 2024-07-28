package lk.ijse.dep12.relationship.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.relationship.jpa.entity.Contact;
import lk.ijse.dep12.relationship.jpa.entity.Student;
import lk.ijse.dep12.relationship.jpa.util.JpaUtil;

public class ManyToMany3 {
    public static void main(String[] args) {
       try( EntityManagerFactory emf= JpaUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager()){
           EntityTransaction tx = em.getTransaction();
           try{
               tx.begin();

               Contact contact = em.find(Contact.class, "091-1000908");
               System.out.println(contact.getStudent());
               Student st1 = em.find(Student.class, "123456789V");
               //st1.getContact();


               tx.commit();
           }catch (Throwable e){
               e.printStackTrace();
               tx.rollback();
           }
       }
    }
}
