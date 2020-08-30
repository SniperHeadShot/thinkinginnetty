package com.bat.netty.customprotocol.client.handler;

import com.bat.netty.customprotocol.entity.Header;
import com.bat.netty.customprotocol.entity.NettyMessage;
import com.bat.netty.customprotocol.enums.MessageType;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.concurrent.TimeUnit;

/**
 * 自定义登陆认证客户端
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 10:36
 **/
public class HeartbeatReqHandler extends ChannelHandlerAdapter {

    private ScheduledFuture<?> heartBeatTask;

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        NettyMessage message = (NettyMessage) msg;
        if (message != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            // TODO 这里忽略了返回结果，默认认证成功
            heartBeatTask = ctx.executor().scheduleAtFixedRate(() -> {
                NettyMessage heartMessage = buildHeatBeat();
                System.out.println("Client send heart beat message to server : ---> " + heartMessage);
                ctx.writeAndFlush(heartMessage);
            }, 0, 5000, TimeUnit.MILLISECONDS);
        } else if (message != null && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
            System.out.println("Client receive server heart beat message : ---> " + message);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_REQ.value());
        message.setHeader(header);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("LoginAuthReqHandler#exceptionCaught() ... " + cause.getMessage());

        // 关闭心跳任务
        if (heartBeatTask != null) {
            heartBeatTask.cancel(true);
            heartBeatTask = null;
        }

        ctx.fireExceptionCaught(cause);
    }
}