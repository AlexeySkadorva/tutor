package by.bsu.tutor.models.entity.relation;

import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.order.LessonType;
import by.bsu.tutor.models.entity.order.OrderLesson;
import by.bsu.tutor.models.entity.tutor.Tutor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor

@Entity
@Table(name = "client_tutor_relations")
public class ClientTutorRelation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<ClientTutorLesson> clientTutorLessons;

    @Column(name = "actual")
    private Boolean actual = true;

    @Column(name = "created_date")
    private Date createdDate;

    @Transient
    private List<Integer> notes;

    @OneToMany
    @JoinColumn(name = "client_tutor_relation_id")
    List<HistoryLesson> historyLessons;


    @PrePersist
    private void updateCreatedDate() {
        if (null == this.createdDate) {
            this.createdDate = new Date();
        }
    }

    @PostLoad
    private void updateNotes() {
        notes = this.historyLessons.stream().map(HistoryLesson::getRating).collect(Collectors.toList());
    }

    public ClientTutorRelation(Client client, Tutor tutor) {
        this.client = client;
        this.tutor = tutor;
    }

}
