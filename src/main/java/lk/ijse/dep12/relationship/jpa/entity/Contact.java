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
@Table(name = "contact")
public class Contact implements Serializable {
    @Id
    private String contact;
    @ManyToOne
    @JoinColumn(name = "student_nic",referencedColumnName = "nic")
    private Student student;
}
