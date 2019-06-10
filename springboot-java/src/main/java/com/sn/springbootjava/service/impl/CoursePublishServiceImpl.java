package com.sn.springbootjava.service.impl;

import com.sn.springbootjava.config.CoursePublishEvent;
import com.sn.springbootjava.entity.Course;
import com.sn.springbootjava.repository.CourseRepository;
import com.sn.springbootjava.service.CoursePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author sn
 */
@Service
public class CoursePublishServiceImpl implements CoursePublishService, ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void register(Course course) {
        courseRepository.save(course);
        applicationEventPublisher.publishEvent(new CoursePublishEvent(course));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
