package by.bsu.tutor.models.entity.note;

import by.bsu.tutor.models.entity.client.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor

@Entity
@Table(name = "notes")
public class SystemNote extends Note {

//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private Client client;

    @Column(name = "name")
    private String name;

}
