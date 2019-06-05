package com.sn.springbootjava.service.impl;

import com.sn.springbootjava.annotation.ControllerAspectAnnotation;
import com.sn.springbootjava.dto.CommonDTO;
import com.sn.springbootjava.service.ControllerAspectService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import com.sn.springbootjava.constant.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author sn
 */
@Service
@Aspect
@Slf4j
public class ControllerAspectServiceImpl implements ControllerAspectService {

    @Override
    @Pointcut("@annotation(com.sn.springbootjava.annotation.ControllerAspectAnnotation)")
    public void controllerAspect() {

    }


    @Override
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String description = this.getAspectLogDescription(joinPoint);
        log.info("=====start=====");
        // 打印描述信息
        log.info("annotation description: {}", description);
        // 打印请求 url
        log.info("URL: {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method: {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP: {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args: {}", joinPoint.getArgs());
    }

    @Override
    @Around("controllerAspect()")
    public <T> CommonDTO<T> doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        CommonDTO<T> commonDTO = null;
        try {
            Object object = proceedingJoinPoint.proceed();
            commonDTO = (CommonDTO<T>) object;
            commonDTO.setStatus(HttpStatus.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            commonDTO.setStatus(HttpStatus.HTTP_INTERNAL_ERROR);
            commonDTO.setMessage(e.getMessage());
        }
        return commonDTO;
    }


    @Override
    @After("controllerAspect()")
    public void doAfter() throws Throwable {
        log.info("=====End=====");
    }

    private String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    description.append(method.getAnnotation(ControllerAspectAnnotation.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }
}
