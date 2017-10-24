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
@Table(name = "languages")
public class Language extends BaseEnum {

    public enum Code {
        RUSSIAN,
        BELARUSSIAN,
        ENGLISH,
        GERMAN,
        FRENCH,
        SPAIN,
        ITALIAN,
        CHINESE,
        POLISH
    }

}
