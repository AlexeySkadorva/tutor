package by.bsu.tutor.models.entity.user;

import by.bsu.tutor.models.entity.base.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "secondname")
    private String secondName;

    @DateTimeFormat(pattern="dd.MM.yyyy")
    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "photo")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Transient
    private Integer age;

//    @PrePersist
//    private void updateCreatedDate(){
//        if(null == this.createdDate) {
//            this.createdDate = new Date();
//        }
//    }

    @PostLoad
    private void calculateAge(){
        if(null != this.birthDate) {
            LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate birthDate = this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            this.age = currentDate.getYear() - birthDate.getYear() + 1;
        }
    }

    public String getFullName() {
        return Stream.of(lastName, firstName, secondName).filter(n -> n != null)
                .collect(Collectors.joining(StringUtils.SPACE));
    }

}
