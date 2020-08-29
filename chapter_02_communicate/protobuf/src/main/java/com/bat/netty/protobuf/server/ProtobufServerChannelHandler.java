package com.bat.netty.protobuf.server;

import com.bat.netty.protobuf.protobuf.UserChatProto;
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
        System.out.println("server receive: " + msg);

        if (msg instanceof UserGreetProto.UserGreet) {
            UserGreetProto.UserGreet req = (UserGreetProto.UserGreet) msg;
            ctx.writeAndFlush(reply(req));
        }

        if (msg instanceof UserChatProto.UserChat) {
            UserChatProto.UserChat req = (UserChatProto.UserChat) msg;
            ctx.writeAndFlush(reply(req));
        }
    }

    private UserGreetProto.UserGreet reply(UserGreetProto.UserGreet req) {
        String username = req.getUsername();
        return UserGreetProto.UserGreet.newBuilder()
                .setUsername("protobuf server")
                .setGreet("Hi, " + username + "!")
                .build();
    }

    private UserChatProto.UserChat reply(UserChatProto.UserChat req) {
        String username = req.getUsername();
        return UserChatProto.UserChat.newBuilder()
                .setUsername("protobuf server")
                .setChat("i am ok, and you, " + username + "?")
                .build();
    }
}
