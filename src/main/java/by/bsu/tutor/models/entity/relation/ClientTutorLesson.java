package by.bsu.tutor.models.entity.relation;

import by.bsu.tutor.models.entity.LessonDuration;
import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.order.LessonType;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.tutor.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "client_tutor_lesson")
public class ClientTutorLesson extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "relation_id")
    private ClientTutorRelation relation;

    @OneToOne
    @JoinColumn(name = "lesson_type_id")
    private LessonType lessonType;

    @Column(name = "periodicity")
    private Integer periodicity;

    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "lesson_duration_id")
    private LessonDuration duration;

    public ClientTutorLesson(ClientTutorRelation relation, LessonType lessonType, Integer periodicity,
                             Subject subject, LessonDuration duration) {
        this.relation = relation;
        this.lessonType = lessonType;
        this.periodicity = periodicity;
        this.subject = subject;
        this.duration = duration;
    }
}
