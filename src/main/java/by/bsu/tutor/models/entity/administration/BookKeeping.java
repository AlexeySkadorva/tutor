package by.bsu.tutor.models.entity.administration;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.client.Client;
import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "bookkeeping")
public class BookKeeping extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "month_pay")
    private Integer monthPay;

    @Column(name = "all_time_pay")
    private Integer allTimePay;

    @Column(name = "paid_last_month")
    private Boolean paidLastMonth;
}
