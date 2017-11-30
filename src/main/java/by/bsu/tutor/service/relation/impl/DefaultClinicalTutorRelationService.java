package by.bsu.tutor.service.relation.impl;

import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.order.OrderLesson;
import by.bsu.tutor.models.entity.relation.ClientTutorLesson;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.repositories.ClientTutorLessonRepository;
import by.bsu.tutor.repositories.ClientTutorRelationRepository;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.relation.ClientTutorRelationService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultClinicalTutorRelationService extends DefaultCrudService<ClientTutorRelation, ClientTutorRelationRepository>
        implements ClientTutorRelationService {

    private final ClientTutorLessonRepository clientTutorLessonRepository;

    public DefaultClinicalTutorRelationService(@NotNull ClientTutorRelationRepository repository, ClientTutorLessonRepository clientTutorLessonRepository) {
        super(repository);
        this.clientTutorLessonRepository = clientTutorLessonRepository;
    }

    @Override
    public ClientTutorRelation createByOrder(Order order) {
        ClientTutorRelation savedClientTutorRelation =
                super.save(new ClientTutorRelation(order.getClient(), order.getTutor()));
        for(OrderLesson orderLesson : order.getOrderLessons()) {
            ClientTutorLesson clientTutorLesson = new ClientTutorLesson(savedClientTutorRelation,
                    orderLesson.getLessonType(), orderLesson.getPereodicity(),
                    orderLesson.getSubject(), orderLesson.getDuration());
            clientTutorLessonRepository.save(clientTutorLesson);
        }
        return savedClientTutorRelation;
    }

    @Override
    public List<ClientTutorRelation> getByTutorId(@NotNull Long id) {
        List<ClientTutorRelation> clientTutorRelations = repository.findByTutorId(id);
        return clientTutorRelations.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientTutorRelation> getByClientId(@NotNull Long id) {
        List<ClientTutorRelation> clientTutorRelations = repository.findByClientId(id);
        return clientTutorRelations.stream()
                .distinct()
                .collect(Collectors.toList());
    }

}
