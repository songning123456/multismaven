package com.sn.springbootjava.service.impl;

import com.sn.springbootjava.config.CoursePublishEvent;
import com.sn.springbootjava.service.CourseSubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author sn
 */
@Service
@Slf4j
public class CourseSubscriptionServiceImpl implements CourseSubscriptionService, ApplicationListener<CoursePublishEvent> {

    @Override
    public void onApplicationEvent(CoursePublishEvent coursePublishEvent) {
        log.info("邮件服务接到通知，给 {} 发送邮件...", coursePublishEvent.getSource());
    }
}
