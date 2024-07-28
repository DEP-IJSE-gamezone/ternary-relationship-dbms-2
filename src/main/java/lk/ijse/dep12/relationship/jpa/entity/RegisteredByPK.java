package lk.ijse.dep12.relationship.jpa.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisteredByPK implements Serializable {

    private User user;
    private Student student;
    private Batch batch;


}
