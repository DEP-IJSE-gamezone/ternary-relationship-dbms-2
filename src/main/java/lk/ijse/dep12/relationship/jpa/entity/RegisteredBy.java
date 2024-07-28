package lk.ijse.dep12.relationship.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registered_by")
@IdClass(RegisteredByPK.class)
public class RegisteredBy implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name ="username",referencedColumnName = "username")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "student_nic",referencedColumnName = "nic")
    private Student student;
    @Id
    @ManyToOne
    @JoinColumn(name = "batch_id",referencedColumnName = "batch_id")
    private Batch batch;
    private Date date;

}
