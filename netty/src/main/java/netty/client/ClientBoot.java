package netty.client;

import netty.bean.MessageBean;
import netty.config.MyNettyProperties;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 客户端启动器
 * @author gyl
 * @date 2022/5/11 - 15:33
 */
@Component
public class ClientBoot {
    @Autowired
    Bootstrap bootstrap;
    @Autowired
    MyNettyProperties myNettyProperties;

    /**
     * 主端口连接
     * @return
     * @throws InterruptedException
     */
    public Channel connect() throws InterruptedException {
        // 连接服务器
        String host = "1zr5986076.iok.la";
        Integer port = 25207;
//        ChannelFuture channelFuture = bootstrap.connect(myNettyProperties.getHost(), myNettyProperties.getPort()).sync();
        ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
        // 监听关闭
        Channel channel = channelFuture.channel();
        return channel;
    }
    /**
     * 备用端口连接
     * @return
     * @throws InterruptedException
     */
    public Channel connectSlave() throws InterruptedException {
        // 连接服务器
        ChannelFuture channelFuture = bootstrap.connect(myNettyProperties.getHost(), myNettyProperties.getPort()).sync();
        // 监听关闭
        Channel channel = channelFuture.channel();
        channel.closeFuture().sync();
        return channel;
    }

    /**
     * 发送消息到服务器端
     * @return
     */
    public void sendMsg(MessageBean messageBean) throws InterruptedException {
        connect().writeAndFlush(messageBean);
    }
}
