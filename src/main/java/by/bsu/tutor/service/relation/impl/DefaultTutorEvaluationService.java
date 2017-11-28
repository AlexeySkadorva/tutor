package by.bsu.tutor.service.relation.impl;

import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.tutor.TutorEvaluation;
import by.bsu.tutor.repositories.TutorEvaluationRepository;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.relation.ClientTutorRelationService;
import by.bsu.tutor.service.relation.TutorEvaluationService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultTutorEvaluationService extends DefaultCrudService<TutorEvaluation, TutorEvaluationRepository>
        implements TutorEvaluationService {

    private final ClientTutorRelationService relationService;


    public DefaultTutorEvaluationService(@NotNull TutorEvaluationRepository repository,
                                         ClientTutorRelationService relationService) {
        super(repository);
        this.relationService = relationService;
    }

    @NotNull
    @Override
    public TutorEvaluation getMiddleEvaluation(@NotNull Tutor tutor) {
        List<ClientTutorRelation> relations = relationService.getByTutorId(tutor.getId());

        List<TutorEvaluation> evaluations = relations.stream().flatMap(r -> repository.findByRelation(r).stream()).collect(Collectors.toList());
        TutorEvaluation evaluation = new TutorEvaluation();
        if (evaluations.size() != 0) {
            evaluation.setEvaluation(evaluations.stream().mapToInt(TutorEvaluation::getEvaluation).sum() / evaluations.size());
            evaluation.setSociability(evaluations.stream().mapToInt(TutorEvaluation::getSociability).sum() / evaluations.size());
            evaluation.setInterest(evaluations.stream().mapToInt(TutorEvaluation::getInterest).sum() / evaluations.size());
            evaluation.setExplanations(evaluations.stream().mapToInt(TutorEvaluation::getExplanations).sum() / evaluations.size());
            evaluation.setId(evaluations.get(0).getId());
        }
        return evaluation;
    }

}
