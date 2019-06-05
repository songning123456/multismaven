package com.sn.springbootjava.repository;

import com.sn.springbootjava.entity.Course;

import java.util.List;

/**
 * @author sn
 */
public interface CourseRepository extends CrudRepository<Course, Integer> {

    @Override
    List<Course> findAll();
}
