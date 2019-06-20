package com.sn.springbootjava.service;

import com.sn.springbootjava.dto.TeacherDTO;

import java.util.List;

/**
 * @author sn
 */
public interface TeacherService {
    List<TeacherDTO> getAllTeachers();
}
