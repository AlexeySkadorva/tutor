package by.bsu.tutor.models.entity.order;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.tutor.Tutor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @OneToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderLesson> orderLessons;

    @Column(name = "message")
    private String message;

    @Column(name = "created_date")
    private Date createdDate;


    public Order(Tutor tutor, Client client, OrderStatus orderStatus) {
        this.tutor = tutor;
        this.client = client;
        this.orderStatus = orderStatus;
    }

}
