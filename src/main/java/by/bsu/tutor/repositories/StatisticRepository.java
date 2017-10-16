package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.administration.Statistic;

import java.util.List;

public interface StatisticRepository extends BaseRepository<Statistic> {

    List<Statistic> findByRelationId(Long id);

}
