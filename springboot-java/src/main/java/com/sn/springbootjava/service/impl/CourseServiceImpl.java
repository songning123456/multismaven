package com.sn.springbootjava.service.impl;

import com.sn.springbootjava.dto.CourseDTO;
import com.sn.springbootjava.entity.Course;
import com.sn.springbootjava.repository.CourseRepository;
import com.sn.springbootjava.service.CourseService;
import com.sn.springbootjava.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sn
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<CourseDTO> getAllCourse() {
        List<Course> source = courseRepository.findAll();
        List<CourseDTO> target = new ArrayList<>();
        ClassUtil.populateList(source, target, CourseDTO.class);
        return target;
    }
}
