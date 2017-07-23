package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.administration.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticRepository extends BaseRepository<Statistic> {

    List<Statistic> findByRelationId(Long id);

}
