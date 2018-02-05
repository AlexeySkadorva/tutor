package by.bsu.tutor.models.entity.tutor;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.order.OrderLesson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data

@Entity
@Table(name = "tutor_subject_relations")
public class TutorSubject extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
//
//    @JsonIgnoreProperties("tutor")
//    @OneToOne
    @Column(name = "tutor_id")
    private Long tutorId;

    @OneToMany
    @JoinColumn(name = "tutor_subject_id")
    private List<TutorSubjectDuration> tutorSubjectDurations;

    public TutorSubject() {
        this.subject = new Subject();
        this.tutorSubjectDurations = new ArrayList<>(4);
        tutorSubjectDurations.add(new TutorSubjectDuration());
        tutorSubjectDurations.add(new TutorSubjectDuration());
        tutorSubjectDurations.add(new TutorSubjectDuration());
        tutorSubjectDurations.add(new TutorSubjectDuration());
    }

    @Override
    public String toString() {
        return "TutorSubject{" +
                "subject=" + subject +
                ", tutorSubjectDurations=" + tutorSubjectDurations +
                '}';
    }
}
