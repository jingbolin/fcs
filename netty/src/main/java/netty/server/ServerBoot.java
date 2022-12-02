package netty.server;

import netty.config.MyNettyProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 服务器启动类
 * @author gyl
 * @date 2022/5/11 - 15:09
 */
@Component
@Slf4j
public class ServerBoot {
    @Autowired
    ServerBootstrap serverBootstrap;
    @Resource
    NioEventLoopGroup boosGroup;
    @Resource
    NioEventLoopGroup workerGroup;
    @Autowired
    MyNettyProperties nettyProperties;

    /**
     * 开机启动
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        // 绑定端口启动
//        serverBootstrap.bind(nettyProperties.getPort()).sync();
//        serverBootstrap.bind(nettyProperties.getPortSalve()).sync();
        serverBootstrap.bind(7000).sync();
        serverBootstrap.bind(7001).sync();
        log.info("启动Netty多端口服务器: {},{}",nettyProperties.getPort(),nettyProperties.getPortSalve());
    }

    /**
     * 关闭线程池
     */
    @PreDestroy
    public void close() throws InterruptedException {
        log.info("关闭Netty服务器");
        boosGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
