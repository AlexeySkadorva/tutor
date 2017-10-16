package by.bsu.tutor.models.entity.administration;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
@Table(name = "pay_statistic")
public class Statistic extends BaseEntity {

    @Column(name = "date")
    private Date date;

    @OneToOne
    @JoinColumn(name = "relation_id")
    private ClientTutorRelation relation;

    @Column(name = "amount")
    private Integer amount;

}
