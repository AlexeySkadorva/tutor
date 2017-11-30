package by.bsu.tutor.models.entity.order;

import by.bsu.tutor.models.entity.LessonDuration;
import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.tutor.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "orders")
public class OrderLesson extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "lesson_duration_id")
    private LessonDuration duration;

    @OneToOne
    @JoinColumn(name = "lesson_type_id")
    private LessonType lessonType;

    @Column(name = "pereodicity")
    private Integer pereodicity;

    public OrderLesson(Order order, Subject subject, LessonDuration duration,
                       LessonType lessonType, Integer pereodicity) {
        this.order = order;
        this.subject = subject;
        this.duration = duration;
        this.lessonType = lessonType;
        this.pereodicity = pereodicity;
    }
}
