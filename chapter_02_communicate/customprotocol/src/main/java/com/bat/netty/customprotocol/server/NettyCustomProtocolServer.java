package com.bat.netty.customprotocol.server;

import com.bat.netty.customprotocol.codec.decoder.NettyMessageFrameDecoder;
import com.bat.netty.customprotocol.codec.decoder.NettyMessageProtocolDecoder;
import com.bat.netty.customprotocol.codec.encoder.NettyMessageFrameEncoder;
import com.bat.netty.customprotocol.codec.encoder.NettyMessageProtocolEncoder;
import com.bat.netty.customprotocol.constant.Constant;
import com.bat.netty.customprotocol.server.handler.HeartbeatRspHandler;
import com.bat.netty.customprotocol.server.handler.LoginAuthRspHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 自定义协议 服务端
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 9:13
 **/
public class NettyCustomProtocolServer {

    private void bind() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new NettyMessageFrameDecoder());
                            ch.pipeline().addLast(new NettyMessageFrameEncoder());

                            ch.pipeline().addLast(new NettyMessageProtocolDecoder());
                            ch.pipeline().addLast(new NettyMessageProtocolEncoder());

                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                            ch.pipeline().addLast(new LoginAuthRspHandler());
                            ch.pipeline().addLast(new HeartbeatRspHandler());
                        }
                    });

            // 绑定端口，同步等待成功
            b.bind(Constant.PORT).sync();
            System.out.println(String.format("Netty Server start at %s:%d", Constant.REMOTE_IP, Constant.PORT));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NettyCustomProtocolServer().bind();
    }
}
