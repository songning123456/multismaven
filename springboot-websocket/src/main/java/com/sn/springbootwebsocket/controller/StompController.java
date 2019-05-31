package com.sn.springbootwebsocket.controller;

import com.sn.springbootwebsocket.dto.StompDTO;
import com.sn.springbootwebsocket.vo.StompVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sn
 */
@RestController
public class StompController {
    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public StompDTO greeting(StompVO vo) {
        StompDTO stompDTO = new StompDTO();
        stompDTO.setDescription("hello" + vo.getDescription() + "!");
        return stompDTO;
    }
}
