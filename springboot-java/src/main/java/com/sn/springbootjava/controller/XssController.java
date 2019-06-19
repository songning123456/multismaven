package com.sn.springbootjava.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sn
 */
@RestController
@RequestMapping("/xss")
@Slf4j
public class XssController {

    @PostMapping("/string")
    public Object testString(@RequestParam(value = "name", required = false) String name) {
        log.info(name);
        return name;
    }
}
