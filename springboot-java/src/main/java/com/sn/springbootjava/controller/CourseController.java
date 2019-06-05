package com.sn.springbootjava.controller;

import com.sn.springbootjava.annotation.ControllerAspectAnnotation;
import com.sn.springbootjava.dto.CommonDTO;
import com.sn.springbootjava.dto.CourseDTO;
import com.sn.springbootjava.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sn
 */
@RestController
@Slf4j
@RequestMapping(value = "/springboot/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping("/search-all")
    @ControllerAspectAnnotation(description = "获取所有课程")
    public CommonDTO<CourseDTO> getAllCourse() {
        CommonDTO<CourseDTO> commonDTO = new CommonDTO<>();
        List<CourseDTO> result = courseService.getAllCourse();
        commonDTO.setData(result);
        return commonDTO;
    }
}
