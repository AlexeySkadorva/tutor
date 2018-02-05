package by.bsu.tutor.service.base;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.dto.SearchForm;
import by.bsu.tutor.models.entity.base.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * Created by lesha on 20.07.2017.
 */
public interface CrudService<T extends BaseEntity> {

    @NotNull
    long getCount();

    @NotNull
    List<T> getAll();

    @NotNull
    Iterable<T> getBySearchForm(@NotNull SearchForm searchForm);

    @NotNull
    T get(@NotNull Long id) throws LogicException;

    @NotNull
    T save(@NotNull T entity) throws LogicException;

    @NotNull
    List<T> save(@NotNull Collection<T> entities) throws LogicException;

    void delete(@NotNull Long id);

}
