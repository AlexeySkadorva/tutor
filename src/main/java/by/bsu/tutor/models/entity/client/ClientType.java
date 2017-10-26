package by.bsu.tutor.models.entity.client;

import by.bsu.tutor.models.entity.base.BaseEnum;
import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "client_type")
public class ClientType extends BaseEnum {

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    private Code code;

    public enum Code {
        PRESCHOOLER,
        SCHOOLBOY,
        PRIMARY_SCHOOL,
        SECONDARY_SCHOOL,
        HIGH_SCHOOL,
        STUDENT,
        ADULT;
    }

}
