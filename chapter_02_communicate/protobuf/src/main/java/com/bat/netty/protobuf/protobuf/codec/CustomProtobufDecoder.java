package com.bat.netty.protobuf.protobuf.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.util.internal.ObjectUtil;

import java.util.List;

/**
 * 自定义 Protobuf 编码器
 *
 * @author ZhengYu
 * @version 1.0 2020/8/31 16:16
 **/
public class CustomProtobufDecoder extends ProtobufDecoder {

    private String prototypeClassname;

    private static final byte[] PROTOCOL_CLASS_NAME = new byte[64];

    private static final boolean HAS_PARSER;

    private final MessageLite prototype;

    static {
        boolean hasParser = false;
        try {
            // MessageLite.getParserForType() is not available until protobuf 2.5.0.
            MessageLite.class.getDeclaredMethod("getParserForType");
            hasParser = true;
        } catch (Throwable t) {
            // Ignore
        }

        HAS_PARSER = hasParser;
    }

    public CustomProtobufDecoder(MessageLite prototype) {
        super(prototype);
        this.prototype = ObjectUtil.checkNotNull(prototype, "prototype").getDefaultInstanceForType();
        this.prototypeClassname = prototype.getClass().getName().substring(prototype.getClass().getName().lastIndexOf(".") + 1);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final byte[] array;
        final int offset;
        final int length = msg.readableBytes();
        if (msg.hasArray()) {
            array = msg.array();
            offset = msg.arrayOffset() + msg.readerIndex();
        } else {
            array = ByteBufUtil.getBytes(msg, msg.readerIndex(), length, false);
            offset = 0;
        }

        try {
            MessageLite messageLite;
            if (HAS_PARSER) {
                messageLite = prototype.getParserForType().parseFrom(array, offset, length, null);
            } else {
                messageLite = prototype.newBuilderForType().mergeFrom(array, offset, length, null).build();
            }

            String protocolTransferClassName = messageLite.getClass().getName().substring(messageLite.getClass().getName().lastIndexOf(".") + 1);

            if (protocolTransferClassName.equals(prototypeClassname)) {
                out.add(messageLite);
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } finally {
            ctx.fireChannelRead(msg);
        }
    }
}
