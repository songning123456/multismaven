package com.sn.springbootjava.repository;

import com.sn.springbootjava.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sn
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

    @Override
    List<Course> findAll();
}
