package com.sn.springbootjava.config;

import com.sn.springbootjava.entity.Course;
import org.springframework.context.ApplicationEvent;

/**
 * @author sn
 */
public class CoursePublishEvent extends ApplicationEvent {

    public CoursePublishEvent(Course course) {
        super(course);
    }
}
