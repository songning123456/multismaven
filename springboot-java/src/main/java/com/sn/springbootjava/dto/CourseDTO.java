package com.sn.springbootjava.dto;

import lombok.Data;

/**
 * @author sn
 */
@Data
public class CourseDTO {
    private Integer id;

    private String courseName;

    private Integer teacherId;
}
