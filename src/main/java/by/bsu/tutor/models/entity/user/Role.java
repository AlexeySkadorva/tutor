package by.bsu.tutor.models.entity.user;

import by.bsu.tutor.models.entity.base.BaseEnum;
import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "roles")
public class Role extends BaseEnum {

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    private Code code;

    public enum Code {
        ADMIN,
        TUTOR,
        CLIENT
    }

}
