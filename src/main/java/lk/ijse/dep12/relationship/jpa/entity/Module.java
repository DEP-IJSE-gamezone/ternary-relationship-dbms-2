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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "course_module",
    joinColumns = @JoinColumn(name = "module_code",referencedColumnName = "code"),
    inverseJoinColumns = @JoinColumn(name = "course_code",referencedColumnName = "code"))
    private List<Course> courses=new ArrayList<>();


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "take",
    joinColumns = @JoinColumn(name = "module_code",referencedColumnName = "code"),
    inverseJoinColumns = @JoinColumn(name = "student_nic",referencedColumnName = "nic"))
    private List<Student> students=new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "take",
            joinColumns =  @JoinColumn(name = "module_code",referencedColumnName = "code"),
            inverseJoinColumns =@JoinColumn(name = "batch_id",referencedColumnName = "batchId"))
    private List<Module> module=new ArrayList<Module>();



    public Module(String code, String name, BigDecimal credit, Type type) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.type = type;
    }

}
