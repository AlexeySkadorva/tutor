package by.bsu.tutor.models.entity.base;

import lombok.Data;

import javax.persistence.*;

@Data

@MappedSuperclass
public class BaseEnum {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
