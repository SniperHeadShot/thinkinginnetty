package com.bat.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;

/**
 * WebSocket 服务端
 *
 * @author ZhengYu
 * @version 1.0 2020/8/28 10:55
 **/
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) {
        System.out.println("WebSocketServerHandler#messageReceived()");
        if (msg instanceof FullHttpMessage) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
            return;
        }

        if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        System.out.println("WebSocketServerHandler#handleHttpRequest()");

        // 如果HTTP解码失败，返回Http异常
        if (!req.getDecoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            ctx.writeAndFlush("not support http protocol");
            return;
        }

        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://127.0.0.1:10000/websocket", null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        System.out.println("WebSocketServerHandler#handleWebSocketFrame()");

        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(
                    new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }
        // 返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        ctx.channel().writeAndFlush(new TextWebSocketFrame(request + " , 欢迎使用Netty WebSocket服务，现在时刻：" + new java.util.Date().toString()));
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) {
        System.out.println("WebSocketServerHandler#channelWritabilityChanged()");
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("WebSocketServerHandler#channelInactive()");
    }
}
