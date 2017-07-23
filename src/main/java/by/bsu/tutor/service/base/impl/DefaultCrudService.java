package by.bsu.tutor.service.base.impl;

import by.bsu.tutor.exceptions.EntityNotExistException;
import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.dto.SearchForm;
import by.bsu.tutor.models.entity.base.BaseEntity;
import by.bsu.tutor.repositories.BaseRepository;
import by.bsu.tutor.service.base.CrudService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultCrudService<T extends BaseEntity, R extends BaseRepository<T>> implements CrudService<T> {

    protected static final int DEFAULT_PAGE = 0;
    protected static final int DEFAULT_PAGE_SIZE = 5;

    protected final R repository;


    public DefaultCrudService(@NotNull R repository) {
        this.repository = repository;
    }

    @Override
    public long getCount() {
        return repository.count();
    }

    @NotNull
    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @NotNull
    @Override
    public Iterable<T> getBySearchForm(SearchForm searchForm){
        Pageable pageSpecification;
        int pageNumber = searchForm.getPageNumber() == null ? DEFAULT_PAGE : searchForm.getPageNumber() - 1;
        pageSpecification = new PageRequest(pageNumber, searchForm.getPageSize());

        return repository.findAll(pageSpecification);
    }

    @NotNull
    @Override
    public T get(@NotNull Long id) throws LogicException {
        T entity = repository.findOne(id);

        if (null == entity) {
            throw new EntityNotExistException(String.format("Entity with id %s not found!", id), "id");
        }

        return entity;
    }

    @NotNull
    @Override
    public T save(@NotNull T entity) {
        Assert.isNull(entity.getId(), String.format("Entity with id %s already exist", entity.getId()));
        return repository.save(entity);
    }

    @NotNull
    @Override
    public List<T> save(@NotNull Collection<T> entities) throws LogicException {
        List<T> savedEntities = new ArrayList<>();

        for (T entity : entities) {
            savedEntities.add(this.save(entity));
        }
        return savedEntities;
    }

    @Override
    public void delete(@NotNull Long id) {
        repository.delete(id);
    }

}