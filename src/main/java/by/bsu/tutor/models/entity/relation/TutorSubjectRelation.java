package by.bsu.tutor.models.entity.relation;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.tutor.Subject;
import by.bsu.tutor.models.entity.tutor.Tutor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data

@Entity
@Table(name = "tutor_subject_relations")
public class TutorSubjectRelation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
