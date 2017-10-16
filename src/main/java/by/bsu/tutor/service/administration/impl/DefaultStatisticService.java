package by.bsu.tutor.service.administration.impl;

import by.bsu.tutor.models.entity.administration.Statistic;
import by.bsu.tutor.repositories.StatisticRepository;
import by.bsu.tutor.service.administration.StatisticService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DefaultStatisticService extends DefaultCrudService<Statistic, StatisticRepository> implements StatisticService {

    @Autowired
    public DefaultStatisticService(@NotNull StatisticRepository repository) {
        super(repository);
    }

    @Override
    public List<Statistic> getByRelationId(@NotNull Long id) {
        return repository.findByRelationId(id);
    }

}
