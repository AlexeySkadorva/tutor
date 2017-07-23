package by.bsu.tutor.service.administration;

import by.bsu.tutor.models.entity.administration.Statistic;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface StatisticService extends CrudService<Statistic> {

    List<Statistic> getByRelationId(@NotNull Long id);

}
