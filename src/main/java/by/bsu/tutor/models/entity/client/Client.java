package by.bsu.tutor.models.entity.client;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data

@Entity
@Table(name = "client")
public class Client extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "grade")
    private Integer grade;

    @OneToOne
    @JoinTable(name = "client_tutor_relations", joinColumns = { @JoinColumn(name = "client_id") },
            inverseJoinColumns = { @JoinColumn(name = "tutor_id") })
    private Tutor tutor;

    @OneToMany
    @JoinTable(name = "client_tutor_relations", joinColumns = { @JoinColumn(name = "client_id") },
            inverseJoinColumns = { @JoinColumn(name = "id") })
    private List<ClientTutorRelation> relations;

}
