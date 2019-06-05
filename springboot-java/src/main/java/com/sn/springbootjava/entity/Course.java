package com.sn.springbootjava.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author sn
 */
@Data
@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "courseName", columnDefinition = "VARCHAR(100) COMMENT '课程名称'")
    private String courseName;

    @Column(name = "teacherId", columnDefinition = "VARCHAR(100) COMMENT '教师序号'")
    private Integer teacherId;
}
