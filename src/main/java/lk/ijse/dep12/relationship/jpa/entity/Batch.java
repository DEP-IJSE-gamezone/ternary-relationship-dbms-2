package lk.ijse.dep12.relationship.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "batch")
@ToString(exclude = "registeredByList")
public class Batch implements Serializable {
    @Id
    @Column(name = "batch_id")
    private String batchId;
    private String duration;
    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "code")
    private Course course;
    private BigDecimal fee;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "batch", cascade = CascadeType.PERSIST)
    private List<RegisteredBy> registeredByList = new ArrayList<>();



    public Batch(String batchId, String duration, Course course, BigDecimal fee) {
        this.batchId = batchId;
        this.duration = duration;
        this.course = course;
        this.fee = fee;
    }


    public Batch(String batchId, String duration, Course course, BigDecimal fee, List<RegisteredBy> registeredByList) {
        if (registeredByList != null && !registeredByList.isEmpty()) {
            registeredByList.stream().filter(st -> st.getBatch() == null).forEach(st -> st.setBatch(this));
            registeredByList.forEach(st -> {

                if (st.getBatch() != this) {
                    throw new IllegalStateException("Student %s is already registered to the %s Batch".formatted(st.getStudent().getNic(), st.getBatch().batchId));
                }
            });
        }
        this.batchId = batchId;
        this.duration = duration;
        this.course = course;
        this.fee = fee;
        this.registeredByList = registeredByList;
    }

    @PrePersist
    public void beforePersist() {
        if (getRegisteredByList().isEmpty()) {
            throw new IllegalStateException("RegisteredByList is empty");
        }
    }



}
