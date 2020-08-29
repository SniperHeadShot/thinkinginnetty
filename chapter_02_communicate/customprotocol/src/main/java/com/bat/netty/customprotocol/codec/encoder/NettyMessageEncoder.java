package com.bat.netty.customprotocol.codec.encoder;

import com.alibaba.fastjson.JSONObject;
import com.bat.netty.customprotocol.entity.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * 消息编码类
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 9:19
 **/
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf sendBuf) throws Exception {
        if (msg == null || msg.getHeader() == null) {
            throw new Exception("The encode message is null");
        }
        sendBuf.writeInt(msg.getHeader().getCrcCode());
        sendBuf.writeInt(msg.getHeader().getLength());
        sendBuf.writeLong(msg.getHeader().getSessionID());
        sendBuf.writeByte(msg.getHeader().getType());
        sendBuf.writeByte(msg.getHeader().getPriority());

        // 附件编码
        sendBuf.writeInt(msg.getHeader().getAttachment().size());
        msg.getHeader().getAttachment().forEach((key, value) -> {
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            sendBuf.writeInt(keyBytes.length);
            sendBuf.writeBytes(JSONObject.toJSONBytes(value));
        });

        if (msg.getBody() != null) {
            byte[] bodyBytes = JSONObject.toJSONBytes(msg.getBody());
            sendBuf.writeInt(bodyBytes.length);
            sendBuf.writeBytes(bodyBytes);
        } else {
            sendBuf.writeInt(0);
        }

        // 更新整个消息长度
        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }
}
