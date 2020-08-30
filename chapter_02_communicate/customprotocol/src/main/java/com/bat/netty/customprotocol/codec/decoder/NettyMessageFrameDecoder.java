package com.bat.netty.customprotocol.codec.decoder;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 协议解码 - 用于封帧，解决粘包、半包问题
 *
 * @author ZhengYu
 * @version 1.0 2020/8/30 15:54
 **/
public class NettyMessageFrameDecoder extends LengthFieldBasedFrameDecoder {

    public NettyMessageFrameDecoder( ) {
        super(Integer.MAX_VALUE, 0, 4, 0, 4);
    }
}
