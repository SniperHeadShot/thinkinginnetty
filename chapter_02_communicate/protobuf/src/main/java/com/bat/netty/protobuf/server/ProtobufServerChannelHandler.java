package com.bat.netty.protobuf.server;

import com.bat.netty.protobuf.protobuf.UserGreetProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 自定义 {@link io.netty.channel.ChannelHandler} 实现
 *
 * @author ZhengYu
 * @version 1.0 2020/8/27 15:59
 **/
public class ProtobufServerChannelHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("ProtobufServerChannelHandler#channelActive() ...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("ProtobufServerChannelHandler#channelRead() ...");
        UserGreetProto.UserGreet req = (UserGreetProto.UserGreet) msg;
        System.out.println("server receive: " + req.getGreet());
        ctx.writeAndFlush(reply(req));
    }

    private UserGreetProto.UserGreet reply(UserGreetProto.UserGreet req) {
        String username = req.getUsername();
        return UserGreetProto.UserGreet.newBuilder()
                .setUsername("protobuf server")
                .setGreet("Hi, " + username + "!")
                .build();
    }
}
