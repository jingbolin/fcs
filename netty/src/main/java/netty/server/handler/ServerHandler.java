package netty.server.handler;

import netty.common.handler.MessageDecodeHandler;
import netty.common.handler.MessageEncodeHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 定义worker端的处理器
 * @author gyl
 * @date 2022/5/11 - 13:44
 */
public class ServerHandler extends ChannelInitializer<SocketChannel> {
    /**
     * 初始化通道以及配置对应管道的处理器
     * @param socketChannel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MessageDecodeHandler());
        pipeline.addLast(new MessageEncodeHandler());
        pipeline.addLast(new ServerListenerHandler());
    }
}
