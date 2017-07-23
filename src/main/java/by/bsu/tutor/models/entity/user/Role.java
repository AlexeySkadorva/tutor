package by.bsu.tutor.models.entity.user;

import by.bsu.tutor.models.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

}
