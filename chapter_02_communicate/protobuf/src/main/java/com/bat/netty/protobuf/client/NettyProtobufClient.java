package com.bat.netty.protobuf.client;

import com.bat.netty.protobuf.protobuf.UserChatProto;
import com.bat.netty.protobuf.protobuf.UserGreetProto;
import com.bat.netty.protobuf.protobuf.codec.CustomProtobufDecoder;
import com.bat.netty.protobuf.protobuf.codec.CustomProtobufEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Netty Protobuf Client
 *
 * @author ZhengYu
 * @version 1.0 2020/8/27 16:25
 **/
public class NettyProtobufClient {

    public static void main(String[] args) {
        new NettyProtobufClient().connect("127.0.0.1", 9090);
    }

    public void connect(String host, int port) {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            ch.pipeline().addLast(new CustomProtobufDecoder(UserGreetProto.UserGreet.getDefaultInstance()));
                            ch.pipeline().addLast(new CustomProtobufDecoder(UserChatProto.UserChat.getDefaultInstance()));
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());
//                            ch.pipeline().addLast(new ProtobufServerUserGreetChannelHandler());
//                            ch.pipeline().addLast(new ProtobufServerUserChatChannelHandler());
                        }
                    });

            // 发起异步连接操作
            ChannelFuture future = b.connect(host, port).sync();

            future.channel().writeAndFlush(buildGreet(1));
            future.channel().writeAndFlush(buildChat(2));

            // 等待客户端链路关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    private static UserGreetProto.UserGreet buildGreet(int index) {
        return UserGreetProto.UserGreet.newBuilder()
                .setUsername("protobuf client" + index)
                .setGreet("do you know " + index)
                .build();
    }

    private static UserChatProto.UserChat buildChat(int index) {
        return UserChatProto.UserChat.newBuilder()
                .setUsername("protobuf client" + index)
                .setChat("how are you?")
                .build();
    }
}
