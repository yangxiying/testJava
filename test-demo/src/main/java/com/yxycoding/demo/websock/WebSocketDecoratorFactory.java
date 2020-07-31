package com.yxycoding.demo.websock;/*
 * @author yangxy
 * @date 2020/7/31 14:49
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.security.Principal;

/**
 * 服务端和客户端在进行握手挥手时会被执行
 */
@Slf4j
@Component
public class WebSocketDecoratorFactory  implements WebSocketHandlerDecoratorFactory {

    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                log.info("有人连接啦  sessionId = {}", session.getId());//返回的是唯一的会话标识符
                //Principal: 委托人的抽象概念,可以是公司id，名字，用户唯一识别token等
                Principal principal = session.getPrincipal();//经过身份验证，返回Principal实例，未经过身份验证，返回null
                if (principal != null) {
                    log.info("key = {} 存入", principal.getName());
                    // 身份校验成功，缓存socket连接
                    SocketManager.add(principal.getName(), session);
                }


                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                log.info("有人退出连接啦  sessionId = {}", session.getId());
                Principal principal = session.getPrincipal();
                if (principal != null) {
                    // 身份校验成功，移除socket连接
                    SocketManager.remove(principal.getName());
                }
                super.afterConnectionClosed(session, closeStatus);
            }
        };
    }

}
