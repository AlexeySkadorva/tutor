package by.bsu.tutor.models.entity.tutor;

import by.bsu.tutor.models.entity.LessonDuration;
import by.bsu.tutor.models.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "tutor_subject_duration")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TutorSubjectDuration extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "lesson_duration_id")
    private LessonDuration duration;

    @Column(name = "tutor_subject_id")
    private Long tutorSubjectId;

    @Column(name = "price")
    private int price;

    @Override
    public String toString() {
        return "TutorSubjectDuration{" +
                "duration=" + duration +
                ", price=" + price +
                '}';
    }

}
