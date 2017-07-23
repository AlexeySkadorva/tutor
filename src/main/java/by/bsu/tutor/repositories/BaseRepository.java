package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}