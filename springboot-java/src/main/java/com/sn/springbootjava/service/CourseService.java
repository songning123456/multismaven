package com.sn.springbootjava.service;

import com.sn.springbootjava.dto.CourseDTO;
import com.sn.springbootjava.entity.Course;
import com.sn.springbootjava.vo.CommonVO;
import com.sn.springbootjava.vo.CourseVO;

import java.util.List;

/**
 * @author sn
 */
public interface CourseService {
    List<CourseDTO> getAllCourse();

    void addCourse(CommonVO<CourseVO> commonVO);
}
