package by.bsu.tutor.models.entity.order;

import by.bsu.tutor.models.entity.base.BaseEnum;
import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "order_status")
public class OrderStatus extends BaseEnum {

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    private Code code;

    public enum Code {
        NEW,
        APPROVED,
        DECLINED
    }

}
