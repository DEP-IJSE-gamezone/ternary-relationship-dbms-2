package lk.ijse.dep12.relationship.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "module")
public class Module implements Serializable {
    @Id
    private String code;
    private String name;
    private BigDecimal credit;
    @Enumerated(EnumType.STRING)
    private Type type;

    public static enum Type {
        Optional, Mandatory
    }


}
