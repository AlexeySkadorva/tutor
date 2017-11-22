package by.bsu.tutor.models.entity.note;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.tutor.Tutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tutor_notes")
public class TutorNote extends BaseEntity {

    @Column(name = "note")
    private String comment;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "client_tutor_id")
    private ClientTutorRelation clientTutorRelation;

}
