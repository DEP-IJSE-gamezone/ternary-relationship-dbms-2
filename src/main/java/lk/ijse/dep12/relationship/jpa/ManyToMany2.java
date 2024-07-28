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
import java.util.ArrayList;
import java.util.List;

public class ManyToMany2 {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();

                Course Math = em.find(Course.class, "C001");
                Course English = em.find(Course.class, "C002");
                Course it = em.find(Course.class, "C003");
                Course music = em.find(Course.class, "C004");


                Module m001 = em.find(Module.class, "M001");
                Module m002 = em.find(Module.class, "M002");
                Module m003 = em.find(Module.class, "M003");

                Course science = new Course("C005", "Science");

                System.out.println(m001.getCourses());
                System.out.println("1  ----------------------------------");
                System.out.println(Math.getModules());


                System.out.println(" 2 ----------------------------------");
                Batch b001 = em.find(Batch.class, "B001");
                System.out.println(b001.getCourse());


                System.out.println("3  ----------------------------------");
                User dilini = em.find(User.class, "dilini");


                System.out.println("4  ----------------------------------");
                Student st1 = em.find(Student.class, "123456789V");
                Student st2 = em.find(Student.class, "100456789V");
                // st1.getContact();
                //st1.getBatch();
                //st1.getUser();

                System.out.println("5 ----------------------------------");

                Contact hasun = em.find(Contact.class, "071-1234567");
                System.out.println(hasun.getStudent());

                System.out.println("6 ----------------------------------");
                Student st3 = new Student("12393904V", "Kasun Sampath", "Jafna");
              // Student st4 = new Student("890834111V", "Kasun Sampath", "Jafna");
                RegisteredBy registeredBy = new RegisteredBy(dilini, st3, b001, Date.valueOf(LocalDate.now()));
              //  em.persist(registeredBy);
                Student st5 = new Student("9355903V", "Dilshan", "Colombo");

                System.out.println("7 ----------------------------------");

                List<RegisteredBy> registeredByList = new ArrayList<>();
                RegisteredBy registeredBy1 = new RegisteredBy(dilini, st5, null, Date.valueOf(LocalDate.now()));
                registeredByList.add(registeredBy1);

                Batch m004 = new Batch("M004", "1 yr", it, new BigDecimal("80000"), registeredByList);

                em.persist(m004);


                tx.commit();
            } catch (Throwable e) {
                e.printStackTrace();
                tx.rollback();
            }
        }
    }
}
