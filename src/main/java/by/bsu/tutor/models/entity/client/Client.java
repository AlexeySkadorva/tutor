package by.bsu.tutor.models.entity.client;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.User;
import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "client")
public class Client extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "course")
    private Integer course;

    @ManyToOne
    @JoinColumn(name = "client_type_id")
    private ClientType clientType;

    @Transient
    private ClientParent clientParent;

    @Column(name = "institution_of_education")
    private String institutionOfEducation;

    @OneToOne
    @JoinTable(name = "client_tutor_relations", joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "tutor_id")})
    private Tutor tutor;

    public Client() {
        clientParent = new ClientParent();
    }

}
