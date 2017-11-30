package by.bsu.tutor.models.entity;

import by.bsu.tutor.models.entity.base.BaseEnum;
import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "lesson_duration")
public class LessonDuration extends BaseEnum {

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    private Code code;

    public enum Code {
        ACADEMIC_HOUR,
        HOUR,
        TWO_ACADEMIC_HOUR,
        TWO_HOUR
    }

}
