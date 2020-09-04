package com.bat.netty.customprotocol.client;

import com.bat.netty.customprotocol.codec.decoder.NettyMessageFrameDecoder;
import com.bat.netty.customprotocol.codec.decoder.NettyMessageProtocolDecoder;
import com.bat.netty.customprotocol.codec.encoder.NettyMessageFrameEncoder;
import com.bat.netty.customprotocol.codec.encoder.NettyMessageProtocolEncoder;
import com.bat.netty.customprotocol.constant.Constant;
import com.bat.netty.customprotocol.entity.Header;
import com.bat.netty.customprotocol.entity.NettyMessage;
import com.bat.netty.customprotocol.enums.MessageType;
import com.bat.netty.customprotocol.util.SessionIDUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 自定义协议 客户端
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 9:13
 **/
public class NettyCustomProtocolClient {

    public void connect(String host, int port) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new NettyMessageFrameDecoder());
                            ch.pipeline().addLast(new NettyMessageFrameEncoder());

                            ch.pipeline().addLast(new NettyMessageProtocolDecoder());
                            ch.pipeline().addLast(new NettyMessageProtocolEncoder());

                            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));

//                            ch.pipeline().addLast(new LoginAuthReqHandler());
//                            ch.pipeline().addLast(new HeartbeatReqHandler());
                        }
                    });

            // 发起异步连接操作
            ChannelFuture future = b.connect(host, port).sync();

            future.channel().writeAndFlush(buildHeatBeat());

            // 等待客户端链路关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                // 重连
//                connect(host, port);
//                TimeUnit.MINUTES.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private static NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_REQ.value());
        header.setSessionID(SessionIDUtil.buildSessionID());
        message.setHeader(header);
        return message;
    }

    public static void main(String[] args) {
        new NettyCustomProtocolClient().connect("192.168.9.204", Constant.PORT);
    }
}
