package by.bsu.tutor.models.entity.administration;

import by.bsu.tutor.models.entity.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "history_lessons")
public class HistoryLesson extends BaseEntity {

    @Column(name = "date")
    private Date date;

    @Column(name = "client_tutor_relation_id")
    private Long relationId;

    @Column(name = "homework")
    private String homework;

    @Column(name = "completed_material")
    private String completedMaterial;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "paid")
    private Boolean isPaid;

    @PrePersist
    private void updateCreatedDate() {
        if (null == this.date) {
            this.date = new Date();
        }
    }

    public HistoryLesson(Long relationId) {
        this.relationId = relationId;
    }
}
