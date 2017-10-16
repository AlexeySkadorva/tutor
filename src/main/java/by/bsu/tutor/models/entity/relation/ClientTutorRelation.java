package by.bsu.tutor.models.entity.relation;

import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.tutor.Tutor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data

@Entity
@Table(name = "client_tutor_relations")
public class ClientTutorRelation extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @Column(name = "actual")
    private Boolean actual = true;

    @Column(name = "periodicity")
    private Integer periodicity;

    @Column(name = "created_date")
    private Date createdDate;

    @Transient
    private List<Integer> notes;

    @OneToMany
    @JoinColumn(name = "client_tutor_relation_id")
    List<HistoryLesson> historyLessons;

    @PrePersist
    private void updateCreatedDate(){
        if(null == this.createdDate) {
            this.createdDate = new Date();
        }
    }

    @PostLoad
    private void updateNotes(){
        notes = this.historyLessons.stream().map(HistoryLesson::getRating).collect(Collectors.toList());
    }

}
