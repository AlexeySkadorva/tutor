package by.bsu.tutor.service.tutor.impl;

import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.tutor.TutorEvaluation;
import by.bsu.tutor.repositories.TutorEvaluationRepository;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.tutor.TutorEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTutorEvaluationService extends DefaultCrudService<TutorEvaluation, TutorEvaluationRepository>
        implements TutorEvaluationService {

    private ClientTutorRelationService relationService;


    @Autowired
    public DefaultTutorEvaluationService(@NotNull TutorEvaluationRepository repository,
                                         ClientTutorRelationService relationService) {
        super(repository);
        this.relationService = relationService;
    }

    @NotNull
    @Override
    public TutorEvaluation getMiddleEvaluation(@NotNull Tutor tutor){
        //todo
        List<TutorEvaluation> evaluations = new ArrayList<>();
        TutorEvaluation evaluation = new TutorEvaluation();
        if(evaluations.size() != 0) {
            evaluation.setEvaluation(evaluations.stream().mapToInt(TutorEvaluation::getEvaluation).sum() / evaluations.size());
            evaluation.setSociability(evaluations.stream().mapToInt(TutorEvaluation::getSociability).sum() / evaluations.size());
            evaluation.setInterest(evaluations.stream().mapToInt(TutorEvaluation::getInterest).sum() / evaluations.size());
            evaluation.setExplanations(evaluations.stream().mapToInt(TutorEvaluation::getExplanations).sum() / evaluations.size());
            evaluation.setId(evaluations.get(0).getId());
        }
        return evaluation;
    }
}
