package by.bsu.tutor.models;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "message")
public class Message extends BaseEntity {

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne
    @JoinColumn(name = "user_to_id", nullable = false)
    private User userTo;

    @Column(name = "date", nullable = false)
    private Date date;

    public Message(String text, Integer userToId, Integer userFromId, Date date) {
        this.text = text;
        User userTo = new User();
        userTo.setId(Long.valueOf(userToId));
        User userFrom = new User();
        userFrom.setId(Long.valueOf(userFromId));


        this.user = userFrom;
        this.userTo = userTo;

        this.date = date;
    }
}
