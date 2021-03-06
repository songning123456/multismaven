package com.sn.springbootjava.controller;

import com.sn.springbootjava.annotation.ControllerAspectAnnotation;
import com.sn.springbootjava.annotation.DataSourceAnnotation;
import com.sn.springbootjava.constant.DataSourceTypeConstant;
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

import java.util.Arrays;
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
    @DataSourceAnnotation(DataSourceTypeConstant.FIRST_FOLLOWER)
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

    @PostMapping("/http-convert")
    @ControllerAspectAnnotation(description = "转换流")
    public CommonDTO<CourseDTO> convertStream() {
        CommonDTO<CourseDTO> commonDTO = new CommonDTO<>();
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(8);
        courseDTO.setCourseName("其他");
        commonDTO.setData(Arrays.asList(courseDTO));
        return commonDTO;
    }

    @PostMapping("/xss")
    @ControllerAspectAnnotation(description = "测试xss攻击json格式")
    public CommonDTO<CourseDTO> testXss(@RequestBody CommonVO<CourseVO> commonVO) {
        CourseVO courseVO = commonVO.getCondition();
        CourseDTO courseDTO = new CourseDTO();
        CommonDTO<CourseDTO> commonDTO = new CommonDTO<>();
        ClassUtil.populate(courseVO, courseDTO);
        commonDTO.setData(Arrays.asList(courseDTO));
        return commonDTO;
    }
}
