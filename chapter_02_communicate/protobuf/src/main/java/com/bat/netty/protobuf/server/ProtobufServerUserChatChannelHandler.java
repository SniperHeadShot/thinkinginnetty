package com.bat.netty.protobuf.server;

import com.bat.netty.protobuf.protobuf.UserChatProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 自定义 {@link io.netty.channel.ChannelHandler} 实现
 *
 * @author ZhengYu
 * @version 1.0 2020/8/27 15:59
 **/
public class ProtobufServerUserChatChannelHandler extends SimpleChannelInboundHandler<UserChatProto.UserChat> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserChatProto.UserChat msg) {
        System.out.println("UserChatProto.UserChat" + msg);
        ctx.writeAndFlush(reply(msg));
    }

    private UserChatProto.UserChat reply(UserChatProto.UserChat req) {
        String username = req.getUsername();
        return UserChatProto.UserChat.newBuilder()
                .setUsername("protobuf server")
                .setChat("i am ok, and you, " + username + "?")
                .build();
    }
}
