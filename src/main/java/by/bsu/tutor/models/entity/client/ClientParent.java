package by.bsu.tutor.models.entity.client;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.user.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
@Table(name = "client")
public class ClientParent extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "secondname")
    private String secondName;

    @DateTimeFormat(pattern="dd.MM.yyyy")
    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

}
