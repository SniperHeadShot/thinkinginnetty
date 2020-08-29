package com.bat.netty.customprotocol.server.handler;

import com.bat.netty.customprotocol.entity.Header;
import com.bat.netty.customprotocol.entity.NettyMessage;
import com.bat.netty.customprotocol.enums.MessageType;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 自定义登陆认证服务端
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 10:36
 **/
public class LoginAuthRspHandler extends ChannelHandlerAdapter {

    // 重复登陆校验
    private CopyOnWriteArraySet<String> nodeCheck = new CopyOnWriteArraySet<>();

    private CopyOnWriteArrayList<String> whiteList = new CopyOnWriteArrayList<String>() {{
        add("127.0.0.1");
        add("192.168.9.27");
    }};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        NettyMessage message = (NettyMessage) msg;
        if (message != null && message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            NettyMessage loginResp = null;

            // 重复登陆
            if (nodeCheck.contains(nodeIndex)) {
                loginResp = buildLoginRsp("repeated login!");
            }

            // 白名单
            InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
            String ip = address.getAddress().getHostAddress();
            if (!whiteList.contains(ip)) {
                loginResp = buildLoginRsp(String.format("%s not limit login!", ip));
            }

            if (loginResp == null) {
                nodeCheck.add(nodeIndex);
                loginResp = buildLoginRsp("Welcome visit!");
            }

            System.out.println(String.format("%s login", ip));
            ctx.writeAndFlush(loginResp);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildLoginRsp(Object rspMsg) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(rspMsg);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.fireExceptionCaught(cause);
    }
}