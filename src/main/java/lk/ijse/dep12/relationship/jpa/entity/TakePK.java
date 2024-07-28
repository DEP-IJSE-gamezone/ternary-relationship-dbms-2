package lk.ijse.dep12.relationship.jpa.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakePK implements Serializable {
    private String moduleCode;
    private String batchId;
    private String studentNic;
}
