package com.sn.springbootjava.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author sn
 */
@Data
@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "teacherName", columnDefinition = "VARCHAR(100) COMMENT '教师姓名'")
    private String teacherName;
}
