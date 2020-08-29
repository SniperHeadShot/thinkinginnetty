package com.bat.netty.customprotocol.server.handler;

import com.bat.netty.customprotocol.entity.Header;
import com.bat.netty.customprotocol.entity.NettyMessage;
import com.bat.netty.customprotocol.enums.MessageType;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 自定义登陆认证服务端
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 10:36
 **/
public class HeartbeatRspHandler extends ChannelHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        NettyMessage message = (NettyMessage) msg;
        if (message != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println("Receive client heart beat message : ---> " + message);
            NettyMessage heatBeat = buildHeatBeat();
            ctx.writeAndFlush(heatBeat);
            System.out.println("Send heart beat response message to client : ---> " + heatBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.fireExceptionCaught(cause);
    }
}