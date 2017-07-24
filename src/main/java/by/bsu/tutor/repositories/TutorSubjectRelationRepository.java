package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.relation.TutorSubjectRelation;
import by.bsu.tutor.models.entity.tutor.Subject;

import java.util.List;

public interface TutorSubjectRelationRepository extends BaseRepository<TutorSubjectRelation> {

    List<TutorSubjectRelation> findBySubject(Subject subject);

}
