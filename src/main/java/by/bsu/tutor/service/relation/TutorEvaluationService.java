package by.bsu.tutor.service.relation;

import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.tutor.TutorEvaluation;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;

public interface TutorEvaluationService extends CrudService<TutorEvaluation> {

    @NotNull TutorEvaluation getMiddleEvaluation(@NotNull Tutor tutor);

}
