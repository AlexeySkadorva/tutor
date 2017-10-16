package by.bsu.tutor.models.entity.administration;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
@Table(name = "history_lessons")
public class HistoryLesson extends BaseEntity {

    @Column(name = "date")
    private Date date;

    @OneToOne
    @JoinColumn(name = "client_tutor_relation_id")
    private ClientTutorRelation relation;

    @Column(name = "homework")
    private String homework;

    @Column(name = "completed_material")
    private String completedMaterial;

    @Column(name = "rating")
    private Integer rating;

    @PrePersist
    private void updateCreatedDate(){
        if(null == this.date) {
            this.date = new Date();
        }
    }

}
