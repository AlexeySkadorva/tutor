package by.bsu.tutor.controller.order;

import by.bsu.tutor.models.entity.LessonDuration;
import by.bsu.tutor.models.entity.order.OrderLesson;
import by.bsu.tutor.models.entity.tutor.Subject;
import by.bsu.tutor.models.entity.tutor.TutorSubject;
import by.bsu.tutor.models.entity.tutor.TutorSubjectDuration;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    public Subject subject;

    public List<TutorSubjectDuration> tutorSubjectDurations;

    public OrderDto(Subject subject, List<TutorSubjectDuration> tutorSubjectDurations) {
        this.subject = subject;
        tutorSubjectDurations.forEach(t->t.setTutorSubjectId(null));
        this.tutorSubjectDurations = tutorSubjectDurations;
    }
}
