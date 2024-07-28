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
@Table(name = "take")
@IdClass(TakePK.class)
public class Take implements Serializable {
    @Id
    @JoinColumn(name = "module_code",referencedColumnName = "code")
    @ManyToOne
    private Module moduleCode;
    @Id
    @JoinColumn(name = "batch_id",referencedColumnName = "batch_id")
    @ManyToOne
    private Batch batchId;
    @Id
    @JoinColumn(name = "student_nic",referencedColumnName = "nic")
    @ManyToOne
    private Student studentNic;
}
