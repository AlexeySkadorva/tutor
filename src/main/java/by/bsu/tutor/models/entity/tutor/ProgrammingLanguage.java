package by.bsu.tutor.models.entity.tutor;

import by.bsu.tutor.models.entity.base.BaseEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Alexey Skadorva
 */
@Data

@Entity
@Table(name = "programming_languages")
public class ProgrammingLanguage extends BaseEnum {

    public enum Code {
        JAVA,
        JAVASCRIPT,
        C,
        C_PLUS_PLUS,
        C_SHARP,
        PHP,
        RUBY,
        PYTHON
    }

}
