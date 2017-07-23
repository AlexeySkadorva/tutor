package by.bsu.tutor.models.entity.tutor;

import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "tutor_evaluation")
public class TutorEvaluation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "relation_id")
    private ClientTutorRelation relation;

    @Column(name = "evaluation")
    private Integer evaluation;

    @Column(name = "sociability")
    private Integer sociability;

    @Column(name = "interest")
    private Integer interest;

    @Column(name = "explanations")
    private Integer explanations;

}
