package com.bat.netty.protobuf.server;

import com.bat.netty.protobuf.protobuf.UserGreetProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 自定义 {@link io.netty.channel.ChannelHandler} 实现
 *
 * @author ZhengYu
 * @version 1.0 2020/8/27 15:59
 **/
public class ProtobufServerUserGreetChannelHandler extends SimpleChannelInboundHandler<UserGreetProto.UserGreet> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserGreetProto.UserGreet msg) {
        System.out.println("UserGreetProto.UserGreet" + msg);
        ctx.writeAndFlush(reply(msg));
    }

    private UserGreetProto.UserGreet reply(UserGreetProto.UserGreet req) {
        String username = req.getUsername();
        return UserGreetProto.UserGreet.newBuilder()
                .setUsername("protobuf server")
                .setGreet("Hi, " + username + "!")
                .build();
    }
}
