package lk.ijse.dep12.relationship.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.relationship.jpa.entity.*;
import lk.ijse.dep12.relationship.jpa.entity.Module;
import lk.ijse.dep12.relationship.jpa.util.JpaUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ManyToMany1 {
    public static void main(String[] args) {
       try( EntityManagerFactory emf= JpaUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager()){
           EntityTransaction tx = em.getTransaction();
           try{
               tx.begin();

               Module m001 = new Module("M001", "module 1", new BigDecimal("4.20"), Module.Type.Mandatory);
               Module m002 = new Module("M002", "module 2", new BigDecimal("4"), Module.Type.Mandatory);
               Module m003 = new Module("M003", "module 3", new BigDecimal("3"), Module.Type.Optional);
               Module m004 = new Module("M004", "module 4", new BigDecimal("2.25"), Module.Type.Optional);

               Course c001 = new Course("C001", "Math",List.of(m001,m002,m003));
               Course c002 = new Course("C002", "English",List.of(m001,m002));
               Course c003 = new Course("C003", "IT",List.of(m001));
               Course c004 = new Course("C004", "Music",List.of(m004));

               Batch b001 = new Batch("B001", "6 months", c001, new BigDecimal("100000.78"));
               Batch b002 = new Batch("B002", "4 months", c001, new BigDecimal("50000.78"));

               User user1 = new User("dilini", "Dilini Apsara", "123456");
               User user2 = new User(" dasun", "Dasun sampath", "456789");


               Student Hasun = new Student("123456789V", "Hasun Sampath", "Galle");
               Student Namal = new Student("100456789V", "Namal Silva", "Panadura");

               Contact contact1 = new Contact("071-1234567", Hasun);
               Contact contact2 = new Contact("091-1234567", Hasun);
               Contact contact3 = new Contact("091-1000908", Namal);

               Take take1 = new Take(m001, b001, Hasun);
               Take take2 = new Take(m002, b001, Hasun);
               Take take3 = new Take(m003, b002, Hasun);
               Take take4 = new Take(m003, b002, Namal);
               Take take5 = new Take(m001, b002, Namal);

               RegisteredBy registeredBy = new RegisteredBy(user1, Hasun, b001, Date.valueOf(LocalDate.now()));
             //  RegisteredBy registeredBy2 = new RegisteredBy(user1, Hasun, b002, Date.valueOf(LocalDate.now()));
               RegisteredBy registeredBy3 = new RegisteredBy(user2, Namal, b002, Date.valueOf(LocalDate.now()));

               List.of(m001,m002,m003,m004,c001,c002,c003,c004,b001,b002,user1,user2,Hasun,Namal,contact1,contact2,
                       contact3,take1,take2,take3,take4,take5,registeredBy,registeredBy3).forEach(em::persist);
               tx.commit();
           }catch (Throwable e){
               e.printStackTrace();
               tx.rollback();
           }
       }
    }
}
