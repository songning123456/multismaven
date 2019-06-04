package com.sn.springbootjava.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author sn
 */
@Data
@Entity(name = "course")
public class Course {

    private Long id;

    private String courseName;

    private Long teacherId;
}
