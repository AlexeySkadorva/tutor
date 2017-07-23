package by.bsu.tutor.models.entity.tutor;

import by.bsu.tutor.models.entity.base.BaseEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data

@Entity
@Table(name = "subjects")
public class Subject extends BaseEnum {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

}
