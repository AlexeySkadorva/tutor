package by.bsu.tutor.models.entity.order;

import by.bsu.tutor.models.entity.base.BaseEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Alexey Skadorva
 */
@Data

@Entity
@Table(name = "lesson_types")
public class LessonType extends BaseEnum {

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    @JsonIgnore
    @JsonIgnoreProperties
    private LessonType.Code code;

    public enum Code {
        BY_SKYPE,
        AT_HOME_CLIENT,
        AT_HOME_TUTOR
    }

}
