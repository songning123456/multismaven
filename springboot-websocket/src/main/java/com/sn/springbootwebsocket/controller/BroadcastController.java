package com.sn.springbootwebsocket.controller;

import com.sn.springbootwebsocket.configure.broadcast.MyWebSocket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sn
 */
@RestController
public class BroadcastController {

    @GetMapping("/broadcast")
    public void broadcast(){
        MyWebSocket.broadcast();
    }
}
