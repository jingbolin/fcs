package netty.server.handler.iso8583;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 消息编码器  模仿StringEncoder
 * 将返回的数据编成byte数组返回
 * @author gyl
 * @date 2022/5/11 - 13:51
 */
public class MsgEncodeHandler extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf buf) throws Exception {
        if(msg instanceof byte[]){
            buf.writeBytes((byte[])msg);
        }else if (msg instanceof String) {
            byte[] data = msg.toString().getBytes();
            buf.writeBytes(data);
        }
    }
}
