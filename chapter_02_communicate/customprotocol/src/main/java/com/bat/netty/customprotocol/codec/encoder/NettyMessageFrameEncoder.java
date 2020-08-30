package com.bat.netty.customprotocol.codec.encoder;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * 协议编码
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 9:19
 **/
public class NettyMessageFrameEncoder extends LengthFieldPrepender {

    public NettyMessageFrameEncoder() {
        super(4);
    }
}
