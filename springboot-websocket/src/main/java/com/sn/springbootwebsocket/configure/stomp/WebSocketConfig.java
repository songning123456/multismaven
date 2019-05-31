package com.sn.springbootwebsocket.configure.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author sn
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private MyChannelInterceptor myChannelInterceptor;

    /**
     * 项目启动时第一步
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(myChannelInterceptor);
    }

    /**
     * 项目启动时第二步
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册STOMP协议节点,同时指定使用SockJS协议
        registry.addEndpoint("/stomp-websocket").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 项目启动时第三步
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 客户端需要把消息发送到/message/xxx地址
        registry.setApplicationDestinationPrefixes("/message");
        // 服务端广播消息的路径前缀，客户端需要相应订阅/topic/yyy这个地址的消息
        registry.enableSimpleBroker("/topic");
    }
}
