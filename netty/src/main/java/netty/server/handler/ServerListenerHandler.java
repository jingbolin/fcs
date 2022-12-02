package netty.server.handler;

import io.netty.channel.group.ChannelGroup;
import netty.bean.MessageBean;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * server端监听消息处理器
 * Sharable 表明可以被多个channel一起使用
 * @author gyl
 * @date 2022/5/11 - 14:23
 */
@Slf4j
@ChannelHandler.Sharable
public class ServerListenerHandler extends SimpleChannelInboundHandler<MessageBean> {

    @Autowired
    ChannelGroup channelGroup;

    /**
     * 客户端上线的时候调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}客户端连接进来了",ctx.channel().remoteAddress());
        ctx.fireChannelActive();
    }

    /**
     * 客户端掉线的时候调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}连接断开了",ctx.channel().remoteAddress());
        ctx.fireChannelInactive();
    }


    /**
     * 读取客户端信息
     * @param channelHandlerContext
     * @param messageBean
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBean messageBean) throws Exception {
        String remoteAddress = channelHandlerContext.channel().remoteAddress().toString();
        log.info("来自客户端{}的消息{}", remoteAddress,new String(messageBean.getContent(), CharsetUtil.UTF_8));
        channelHandlerContext.writeAndFlush(new MessageBean("ok"));
    }


    /**
     * 异常发生时候调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.error("{}连接出异常了",ctx.channel().remoteAddress());
        ctx.close();
    }

}
