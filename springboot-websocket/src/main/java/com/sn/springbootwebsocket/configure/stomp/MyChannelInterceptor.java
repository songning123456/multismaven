package com.sn.springbootwebsocket.configure.stomp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.text.MessageFormat;

/**
 * @author sn
 */
@Component
@Slf4j
public class MyChannelInterceptor implements ChannelInterceptor {

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();

        //用户已经断开连接
        if (StompCommand.DISCONNECT.equals(command)) {
            String user = "";
            Principal principal = accessor.getUser();
            if (principal != null && StringUtils.isEmpty(principal.getName())) {
                user = principal.getName();
            } else {
                user = accessor.getSessionId();
            }

            log.debug(MessageFormat.format("用户{0}的WebSocket连接已经断开", user));
        }
    }
}
