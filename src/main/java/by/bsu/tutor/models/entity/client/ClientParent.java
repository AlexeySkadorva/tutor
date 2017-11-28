package by.bsu.tutor.models.entity.client;

import by.bsu.tutor.models.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data

@Entity
@Table(name = "client_parent_info")
public class ClientParent extends BaseEntity {

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "secondname")
    private String secondName;

    @Column(name = "phone_number")
    private String phoneNumber;

}
