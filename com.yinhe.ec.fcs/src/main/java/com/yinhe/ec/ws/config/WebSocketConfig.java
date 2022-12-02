package com.yinhe.ec.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author chenyh
 * @since 2018年8月9日上午11:17:26
 */
@Configuration
@EnableWebSocket
@ComponentScan("com.yinhe.ec")
public class WebSocketConfig {
    /**
     * war包部署时不需要该段代码
     * 
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public EndpointConfigure newConfigure() {
        return new EndpointConfigure();
    }
}
