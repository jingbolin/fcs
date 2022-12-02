package netty.server.handler.iso8583;

import cn.hutool.core.util.HexUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 消息解码器 模仿 StringDecoder
 * 适用于一些iso8583协议的消息，转成16进制
 * @author gyl
 * @date 2022/5/11 - 14:08
 */
public class MsgDecodeHandler extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> list) throws Exception {
        byte[] data = new byte[msg.readableBytes()];
        msg.readBytes(data);
        // 转成16进制
        list.add(HexUtil.encodeHexStr(data));
    }
}
