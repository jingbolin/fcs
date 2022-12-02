package netty.common.handler;

import io.netty.util.CharsetUtil;
import netty.bean.MessageBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 消息解码处理器
 * @author gyl
 * @date 2022/5/12 - 10:37
 */
public class MessageDecodeHandler extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int len = byteBuf.readableBytes();
        byte[] content = new byte[len];
        byteBuf.readBytes(content);
        MessageBean messageBean = new MessageBean();
        messageBean.setContent(content);
        messageBean.setLen(len);
        list.add(messageBean);
//        int readableBytes = byteBuf.readableBytes();
//        byte[] bytes =new byte[readableBytes];
//        System.out.println("收到消息"+byteBuf.toString(CharsetUtil.UTF_8));
//        byteBuf.readBytes(bytes);

    }
}
