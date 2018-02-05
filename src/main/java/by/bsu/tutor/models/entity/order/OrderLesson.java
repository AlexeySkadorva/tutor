package by.bsu.tutor.models.entity.order;

import by.bsu.tutor.models.entity.LessonDuration;
import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.tutor.Subject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "order_lesson")
public class OrderLesson extends BaseEntity {

    @Column(name = "order_id")
    private Integer orderId;

    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "lesson_duration_id")
    private LessonDuration duration;

    @OneToOne
    @JoinColumn(name = "lesson_type_id")
    private LessonType lessonType;

    @Column(name = "periodicity")
    private Integer periodicity;

    public OrderLesson(Integer order, Subject subject, LessonDuration duration,
                       LessonType lessonType, Integer periodicity) {
        this.orderId = order;
        this.subject = subject;
        this.duration = duration;
        this.lessonType = lessonType;
        this.periodicity = periodicity;
    }

    public OrderLesson(Subject subject, LessonDuration duration) {
        this.subject = subject;
        this.duration = duration;
    }
}
