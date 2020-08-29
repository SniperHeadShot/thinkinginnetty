package com.bat.netty.customprotocol.server;

import com.bat.netty.customprotocol.codec.decoder.NettyMessageDecoder;
import com.bat.netty.customprotocol.codec.encoder.NettyMessageEncoder;
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
import io.netty.handler.timeout.ReadTimeoutHandler;

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
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(10));
                            ch.pipeline().addLast(new LoginAuthRspHandler());
                            ch.pipeline().addLast(new HeartbeatRspHandler());
                        }
                    });

            // 绑定端口，同步等待成功
            b.bind(Constant.REMOTEIP, Constant.PORT).sync();
            System.out.println(String.format("Netty Server start at %s:%d", Constant.REMOTEIP, Constant.PORT));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NettyCustomProtocolServer().bind();
    }
}
