package com.sn.springbootjava.controller;

import com.sn.springbootjava.annotation.ControllerAspectAnnotation;
import com.sn.springbootjava.dto.CommonDTO;
import com.sn.springbootjava.dto.CourseDTO;
import com.sn.springbootjava.dto.PolymerizationDTO;
import com.sn.springbootjava.dto.TeacherDTO;
import com.sn.springbootjava.service.CourseService;
import com.sn.springbootjava.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author sn
 * 聚合操作
 */
@RestController
@RequestMapping("/polymerization")
public class PolymerizationController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    @RequestMapping("/all")
    @ControllerAspectAnnotation(description = "多线程测试聚合")
    public CommonDTO<PolymerizationDTO> polymerizationAll() throws Exception {
        CommonDTO<PolymerizationDTO> commonDTO = new CommonDTO<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Future<List<CourseDTO>> courseDTOFuture = executorService.submit(() -> {
            try {
                return courseService.getAllCourse();
            } finally {
                countDownLatch.countDown();
            }
        });
        Future<List<TeacherDTO>> teacherFuture = executorService.submit(() -> {
            try {
                return teacherService.getAllTeachers();
            } finally {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        List<CourseDTO> courseDTOList = courseDTOFuture.get();
        List<TeacherDTO> teacherDTOList = teacherFuture.get();
        List<PolymerizationDTO> polymerizationDTOList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            PolymerizationDTO polymerizationDTO = new PolymerizationDTO();
            polymerizationDTO.setCourseName(courseDTOList.get(i).getCourseName());
            polymerizationDTO.setTeacherName(teacherDTOList.get(i).getTeacherName());
            polymerizationDTOList.add(polymerizationDTO);
        }
        commonDTO.setData(polymerizationDTOList);
        return commonDTO;
    }
}
