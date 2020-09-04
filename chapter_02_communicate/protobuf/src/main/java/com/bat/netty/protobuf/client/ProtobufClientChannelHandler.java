//package com.bat.netty.protobuf.client;
//
//import com.bat.netty.protobuf.protobuf.UserChatProto;
//import com.bat.netty.protobuf.protobuf.UserGreetProto;
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.util.Random;
//
///**
// * 自定义 {@link io.netty.channel.ChannelHandler} 实现
// *
// * @author ZhengYu
// * @version 1.0 2020/8/27 15:59
// **/
//public class ProtobufClientChannelHandler extends ChannelHandlerAdapter {
//
//    private static final Random RANDOM = new Random();
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) {
//        System.out.println("ProtobufServerUserGreetChannelHandler#channelActive() ...");
//        for (int i = 0; i < 100; i++) {
//            if (RANDOM.nextBoolean()) {
//                ctx.write(buildGreet(i));
//            } else {
//                ctx.write(buildChat(i));
//            }
//        }
//        ctx.flush();
//    }
//
//    private UserGreetProto.UserGreet buildGreet(int index) {
//        return UserGreetProto.UserGreet.newBuilder()
//                .setUsername("protobuf client" + index)
//                .setGreet("do you know " + index)
//                .build();
//    }
//
//    private UserChatProto.UserChat buildChat(int index) {
//        return UserChatProto.UserChat.newBuilder()
//                .setUsername("protobuf client" + index)
//                .setChat("how are you?")
//                .build();
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        System.out.println("ProtobufServerUserGreetChannelHandler#channelRead() ...");
//        System.out.println("client receive: " + msg);
//    }
//}
