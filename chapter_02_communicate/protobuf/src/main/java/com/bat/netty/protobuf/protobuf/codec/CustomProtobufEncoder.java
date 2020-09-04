package com.bat.netty.protobuf.protobuf.codec;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.nio.charset.Charset;
import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * TODO
 *
 * @author ZhengYu
 * @version 1.0 2020/8/31 16:34
 **/
public class CustomProtobufEncoder extends ProtobufEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) throws Exception {
        String className = msg.getClass().getName().substring(msg.getClass().getName().lastIndexOf(".") + 1);
        byte[] classNameBytes = className.getBytes(Charset.forName("UTF-8"));

        ByteBuf byteBuf;
        if (msg instanceof MessageLite) {
            byte[] bytes = ((MessageLite) msg).toByteArray();
            ByteBuf body = byteBuf = Unpooled.buffer(4 + classNameBytes.length + bytes.length);
            body.writeInt(classNameBytes.length);
            body.writeBytes(classNameBytes);
            body.writeBytes(bytes);
            out.add(byteBuf);
            return;
        }
        if (msg instanceof MessageLite.Builder) {
            out.add(wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray()));
        }
    }
}
