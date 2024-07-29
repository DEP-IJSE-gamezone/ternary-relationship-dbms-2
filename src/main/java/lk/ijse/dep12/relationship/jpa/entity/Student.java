package lk.ijse.dep12.relationship.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
@ToString(exclude = "contacts")
public class Student  implements Serializable {
    @Id
    private String nic;
    private String name;
    private String address;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "student",cascade = {CascadeType.ALL})
    private List<Contact> contacts = new ArrayList<>();

    public Student(String nic, String name, String address) {
        this.nic = nic;
        this.name = name;
        this.address = address;
    }


    public Student(String nic, String name, String address, List<Contact> contacts) {
        if(contacts != null && !contacts.isEmpty()){
            contacts.stream().filter(contact -> contact.getStudent()== null).forEach(contact -> contact.setStudent(this));

            contacts.forEach(contact -> {
                if (contact.getStudent() != this)
                    throw new IllegalStateException("%s contact is already assigned".formatted(contact.getContact()));
            });
        }
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.contacts = contacts;
    }

}
