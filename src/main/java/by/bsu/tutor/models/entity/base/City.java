package by.bsu.tutor.models.entity.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data

@Entity
@Table(name = "city")
public class City extends BaseEntity {

    @Column(name = "name")
    private String name;

}
