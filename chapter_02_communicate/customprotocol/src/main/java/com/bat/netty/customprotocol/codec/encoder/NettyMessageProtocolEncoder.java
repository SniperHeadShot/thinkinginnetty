package com.bat.netty.customprotocol.codec.encoder;

import com.alibaba.fastjson.JSONObject;
import com.bat.netty.customprotocol.entity.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 消息编码 - 用于将业务对象编码为二进制流
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 9:19
 **/
public class NettyMessageProtocolEncoder extends MessageToMessageEncoder<NettyMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) {
        // 申请空间
        ByteBuf sendBuf = ctx.alloc().buffer();
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

        // 将解码结果加入输出
        out.add(sendBuf);
    }
}
