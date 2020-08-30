package com.bat.netty.customprotocol.client.handler;

import com.bat.netty.customprotocol.entity.Header;
import com.bat.netty.customprotocol.entity.NettyMessage;
import com.bat.netty.customprotocol.enums.MessageType;
import com.bat.netty.customprotocol.util.SessionIDUtil;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 客户端 - 心跳发送
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 10:36
 **/
public class LoginAuthReqHandler extends ChannelHandlerAdapter {

    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(buildLoginReq());
    }

    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        header.setSessionID(SessionIDUtil.buildSessionID());
        message.setHeader(header);
        return message;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        NettyMessage message = (NettyMessage) msg;
        if (message != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            String loginResult = (String) message.getBody();
            // TODO 这里忽略了返回结果，默认认证成功
            System.out.println("Login is ok:" + loginResult);
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("LoginAuthReqHandler#exceptionCaught() ... " + cause.getMessage());
        ctx.fireExceptionCaught(cause);
    }
}