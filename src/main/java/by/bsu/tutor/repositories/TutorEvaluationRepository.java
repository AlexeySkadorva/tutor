package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.tutor.TutorEvaluation;

import java.util.List;

public interface TutorEvaluationRepository extends BaseRepository<TutorEvaluation> {

    List<TutorEvaluation> findByRelation(ClientTutorRelation relation);

}

