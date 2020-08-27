package com.bat.netty.protobuf.client;

import com.bat.netty.protobuf.protobuf.UserGreetProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 自定义 {@link io.netty.channel.ChannelHandler} 实现
 *
 * @author ZhengYu
 * @version 1.0 2020/8/27 15:59
 **/
public class ProtobufClientChannelHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("ProtobufServerChannelHandler#channelActive() ...");
        for (int i = 0; i < 100; i++) {
            ctx.write(buildGreet(i));
        }
        ctx.flush();
    }

    private UserGreetProto.UserGreet buildGreet(int index) {
        return UserGreetProto.UserGreet.newBuilder()
                .setUsername("protobuf client" + index)
                .setGreet("do you know " + index)
                .build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("ProtobufServerChannelHandler#channelRead() ...");
        UserGreetProto.UserGreet req = (UserGreetProto.UserGreet) msg;
        System.out.println("client receive: " + req.getGreet());
    }
}
