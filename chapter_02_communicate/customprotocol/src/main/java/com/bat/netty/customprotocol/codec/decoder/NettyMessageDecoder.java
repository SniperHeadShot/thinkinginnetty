package com.bat.netty.customprotocol.codec.decoder;

import com.alibaba.fastjson.JSONObject;
import com.bat.netty.customprotocol.entity.Header;
import com.bat.netty.customprotocol.entity.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息解码类
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 9:19
 **/
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }

        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionID(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());

        int size = frame.readInt();
        if (size > 0) {
            Map<String, Object> attach = new HashMap<>(size);
            byte[] keyArray = new byte[frame.readInt()];
            frame.readBytes(keyArray);

            byte[] valueArray = new byte[frame.readInt()];
            frame.readBytes(keyArray);
            attach.put(new String(keyArray, StandardCharsets.UTF_8), JSONObject.parse(valueArray));
            header.setAttachment(attach);
        }
        message.setHeader(header);

        int readableBytes = frame.readableBytes();
        if (readableBytes > 4) {
            byte[] bodyBytes = new byte[readableBytes];
            frame.readBytes(bodyBytes);
            message.setBody(JSONObject.parse(bodyBytes));
        }
        return message;
    }
}
