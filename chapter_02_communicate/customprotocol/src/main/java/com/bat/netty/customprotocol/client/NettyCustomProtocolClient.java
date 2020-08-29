package com.bat.netty.customprotocol.client;

import com.bat.netty.customprotocol.client.handler.HeartbeatReqHandler;
import com.bat.netty.customprotocol.client.handler.LoginAuthReqHandler;
import com.bat.netty.customprotocol.codec.decoder.NettyMessageDecoder;
import com.bat.netty.customprotocol.codec.encoder.NettyMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

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
                            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            ch.pipeline().addLast(new ReadTimeoutHandler(50));
                            ch.pipeline().addLast(new LoginAuthReqHandler());
                            ch.pipeline().addLast(new HeartbeatReqHandler());
                        }
                    });

            // 发起异步连接操作
            ChannelFuture future = b.connect(host, port).sync();

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

    public static void main(String[] args) {
        new NettyCustomProtocolClient().connect("127.0.0.1", 15000);
    }
}
