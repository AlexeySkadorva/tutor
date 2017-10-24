package by.bsu.tutor.models.entity.order;

import by.bsu.tutor.models.entity.base.BaseEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Alexey Skadorva
 */
@Data

@Entity
@Table(name = "training_types")
public class TrainingType extends BaseEnum {

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    private LessonType.Code code;

    public enum Code {
        PREPARATION_OF_PRESCHOOLERS,// Дошкольники
        PRIMARY_SCHOOL,             // Начальная школа (1-4 класс)
        SECONDARY_SCHOOL,           // Средняя школа (5-9 класс)
        HIGH_SCHOOL,                // Высшая школа (10-11 класс)
        STUDENT,                  // Студенты
        ADULT                       //Взрослый
    }

}