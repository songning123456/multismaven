package com.sn.springbootjava.controller;

import com.sn.springbootjava.annotation.ControllerAspectAnnotation;
import com.sn.springbootjava.dto.CommonDTO;
import com.sn.springbootjava.dto.CourseDTO;
import com.sn.springbootjava.entity.Course;
import com.sn.springbootjava.service.CoursePublishService;
import com.sn.springbootjava.service.CourseService;
import com.sn.springbootjava.util.ClassUtil;
import com.sn.springbootjava.vo.CommonVO;
import com.sn.springbootjava.vo.CourseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    CoursePublishService coursePublishService;

    @RequestMapping("/search-all")
    @ControllerAspectAnnotation(description = "获取所有课程")
    public CommonDTO<CourseDTO> getAllCourse() {
        CommonDTO<CourseDTO> commonDTO = new CommonDTO<>();
        List<CourseDTO> result = courseService.getAllCourse();
        commonDTO.setData(result);
        return commonDTO;
    }

    @PostMapping("/publish-subscription")
    @ControllerAspectAnnotation(description = "发布订阅")
    public CommonDTO<CourseDTO> publishSubscription(@RequestBody CommonVO<CourseVO> commonVO) {
        CourseVO courseVO = commonVO.getCondition();
        Course course = new Course();
        ClassUtil.populate(courseVO, course);
        coursePublishService.register(course);
        return new CommonDTO<>();
    }
}
