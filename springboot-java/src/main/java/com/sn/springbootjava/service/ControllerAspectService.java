package com.sn.springbootjava.service;

import com.sn.springbootjava.dto.CommonDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author sn
 */
public interface ControllerAspectService {

    void controllerAspect();

    void doBefore(JoinPoint joinPoint) throws Throwable;

    <T> CommonDTO<T> doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable;

    void doAfter() throws Throwable;
}
