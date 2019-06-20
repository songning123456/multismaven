package com.sn.springbootjava.repository;

import com.sn.springbootjava.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sn
 */
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

    @Override
    List<Teacher> findAll();
}
