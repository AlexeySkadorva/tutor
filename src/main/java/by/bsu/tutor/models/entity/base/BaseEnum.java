package by.bsu.tutor.models.entity.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data

@MappedSuperclass
public class BaseEnum {

    @Id
    @Column(name = "id")
    private Integer id;

}
