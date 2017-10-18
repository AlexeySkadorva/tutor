package by.bsu.tutor.models.entity.order;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;

    @Column(name = "message")
    private String message;

    @Column(name = "created_date")
    private Date createdDate;


    public Order(Tutor tutor, Client client, String message) {
        this.tutor = tutor;
        this.client = client;
        this.message = message;
    }

}
