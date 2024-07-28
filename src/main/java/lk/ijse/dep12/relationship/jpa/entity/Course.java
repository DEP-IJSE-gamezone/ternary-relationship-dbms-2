package lk.ijse.dep12.relationship.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data

@NoArgsConstructor
@Entity
@Table(name = "course")
@ToString(exclude = "modules")
public class Course implements Serializable {
    @Id
    private String code;
    private String name;

    @ManyToMany(mappedBy = "courses",cascade = CascadeType.PERSIST)
    @Setter(AccessLevel.NONE)
    private List<Module> modules=new ArrayList<>();

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Course(String code, String name, List<Module> modules) {
        if(modules !=null && !modules.isEmpty()){
            modules.stream().filter(module -> !module.getCourses().contains(this)).forEach(module -> module.getCourses().add(this));
        }
        this.code = code;
        this.name = name;
        this.modules = modules;
    }
}
