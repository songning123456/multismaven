package com.sn.springbootwebsocket.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sn
 * 接受websocket请求路径
 * ServerEndpoint注解 监听一个WebSocket请求路径
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {

    /**
     * 保存所有在线socket连接
     */
    private static Map<String, MyWebSocket> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 记录当前在线数目
     */
    private static int count = 0;

    /**
     * 当前连接（每个websocket连入都会创建一个MyWebSocket实例
     */
    private Session session;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 处理连接建立
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketMap.put(session.getId(), this);
        addCount();
        log.info("新的连接加入：{}", session.getId());
    }

    /**
     * 接受消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到客户端{}消息：{}", session.getId(), message);
        try {
            // 返回给客户端
            this.sendMessage("收到消息：" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理错误
     *
     * @param error
     * @param session
     */
    @OnError
    public void onError(Throwable error, Session session) {
        log.info("发生错误{},{}", session.getId(), error.getMessage());
    }

    /**
     * 处理连接关闭
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(this.session.getId());
        reduceCount();
        log.info("连接关闭:{}", this.session.getId());
    }

    /**
     * 发送消息 群发消息
     *
     * @param message
     * @throws IOException
     */
    private void sendMessage(String message) throws IOException {
        // 反转消息
        this.session.getBasicRemote().sendText("Reversed: " + new StringBuilder(message).reverse().toString());
    }

    /**
     * 广播消息(传给客户端)
     */
    public static void broadcast() {
        MyWebSocket.webSocketMap.forEach((k, v) -> {
            try {
                v.sendMessage("这是一条测试广播");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取在线连接数目
     *
     * @return
     */
    public static int getCount() {
        return count;
    }

    /**
     * 操作count，使用synchronized确保线程安全
     */
    private static synchronized void addCount() {
        MyWebSocket.count++;
    }

    private static synchronized void reduceCount() {
        MyWebSocket.count--;
    }
}

