package by.bsu.tutor.models.entity.base;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

}
