package netty.common.handler;

import netty.bean.MessageBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 消息对象编码处理器
 * @author gyl
 * @date 2022/5/12 - 10:36
 */
public class MessageEncodeHandler extends MessageToByteEncoder<MessageBean> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageBean messageBean, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(messageBean.getLen());
        byteBuf.writeBytes(messageBean.getContent());
    }
}
