package by.bsu.tutor.models.entity.tutor;

import by.bsu.tutor.models.entity.base.BaseEnum;
import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "subjects")
public class Subject extends BaseEnum {

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    private Code code;

    public enum Code {
        MATHEMATICS,
        PHYSICS,
        CHEMISTRY,
        BIOLOGY,
        GEOGRAPHY,
        LITERATURE,
        INFORMATICS,
        LANGUAGES,
        HISTORY,
        PROGRAMMING
    }
    
}
