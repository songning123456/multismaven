package com.sn.springbootjava.service.impl;

import com.sn.springbootjava.dto.TeacherDTO;
import com.sn.springbootjava.entity.Teacher;
import com.sn.springbootjava.repository.TeacherRepository;
import com.sn.springbootjava.service.TeacherService;
import com.sn.springbootjava.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sn
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> list = teacherRepository.findAll();
        List<TeacherDTO> result = new ArrayList<>();
        ClassUtil.populateList(list, result, TeacherDTO.class);
        return result;
    }
}
