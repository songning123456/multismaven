package com.sn.springbootjava.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @param <T>
 * @param <ID>
 * @author sn
 */
@NoRepositoryBean
public interface CrudRepository<T, ID> extends Repository<T, ID> {

    /**
     * @param var1
     * @param <S>
     * @return
     */
    <S extends T> S save(S var1);

    /**
     * @param var1
     * @param <S>
     * @return
     */
    <S extends T> Iterable<S> saveAll(Iterable<S> var1);

    /**
     * @param var1
     * @return
     */
    Optional<T> findById(ID var1);

    /**
     * @param var1
     * @return
     */
    boolean existsById(ID var1);

    /**
     * 查询全部
     *
     * @return
     */
    List<T> findAll();

    /**
     * @param var1
     * @return
     */
    List<T> findAllById(Iterable<ID> var1);

    /**
     * @return
     */
    long count();

    /**
     * @param var1
     */
    void deleteById(ID var1);

    /**
     * @param var1
     */
    void delete(T var1);

    /**
     * @param var1
     */
    void deleteAll(Iterable<? extends T> var1);

    /**
     *
     */
    void deleteAll();
}
