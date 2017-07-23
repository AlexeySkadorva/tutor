package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.relation.TutorSubjectRelation;
import by.bsu.tutor.models.entity.tutor.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lesha on 09.05.2017.
 */
public interface TutorSubjectRelationRepository extends BaseRepository<TutorSubjectRelation> {

    List<TutorSubjectRelation> findBySubject(Subject subject);

}
