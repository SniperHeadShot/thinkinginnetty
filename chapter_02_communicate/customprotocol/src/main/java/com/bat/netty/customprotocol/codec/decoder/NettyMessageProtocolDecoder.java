package com.bat.netty.customprotocol.codec.decoder;

import com.alibaba.fastjson.JSONObject;
import com.bat.netty.customprotocol.entity.Header;
import com.bat.netty.customprotocol.entity.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息解码 - 用于将二进制流解码为业务对象
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 9:19
 **/
public class NettyMessageProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) {
        // 解码
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(byteBuf.readInt());
        header.setLength(byteBuf.readInt());
        header.setSessionID(byteBuf.readLong());
        header.setType(byteBuf.readByte());
        header.setPriority(byteBuf.readByte());

        int size = byteBuf.readInt();
        if (size > 0) {
            Map<String, Object> attach = new HashMap<>(size);
            byte[] keyArray = new byte[byteBuf.readInt()];
            byteBuf.readBytes(keyArray);

            byte[] valueArray = new byte[byteBuf.readInt()];
            byteBuf.readBytes(keyArray);
            attach.put(new String(keyArray, StandardCharsets.UTF_8), JSONObject.parse(valueArray));
            header.setAttachment(attach);
        }
        message.setHeader(header);

        int readableBytes = byteBuf.readableBytes();
        if (readableBytes > 4) {
            byte[] bodyBytes = new byte[readableBytes];
            byteBuf.readBytes(bodyBytes);
            message.setBody(JSONObject.parse(bodyBytes));
        }

        // 将解码结果加入输出
        out.add(message);
    }
}
