package by.bsu.tutor.models.entity.note;

import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.tutor.Tutor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data

@Entity
@Table(name = "tutor_notes")
public class TutorNote extends Note {

    @ManyToOne
    @JoinColumn(name = "client_tutor_id")
    private ClientTutorRelation clientTutorRelation;

}
