package com.chileregion.demoMsSql.persistance.repository;

import org.springframework.data.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PagingAndSortingRepository <T, ID> extends CrudRepository<T, ID>
{
    Iterable<T> findAll(Sort var1);

    Page<T> findAll(Pageable var1);
}