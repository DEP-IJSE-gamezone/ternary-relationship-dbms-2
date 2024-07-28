package lk.ijse.dep12.relationship.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student  implements Serializable {
    @Id
    private String nic;
    private String name;
    private String address;

    @JoinTable(name = "take",
    joinColumns =@JoinColumn(name = "student_nic",referencedColumnName = "nic") ,
    inverseJoinColumns = @JoinColumn(name = "module_code",referencedColumnName = "code"))
    @ManyToOne
    private Module module;

    public Student(String nic, String name, String address) {
        this.nic = nic;
        this.name = name;
        this.address = address;
    }
}
