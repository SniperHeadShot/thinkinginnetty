package com.bat.netty.component.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * {@link io.netty.buffer.ByteBuf}
 *
 * @author ZhengYu
 * @version 1.0 2020/8/31 11:16
 **/
public class ByteBufApiTest {
    public static void main(String[] args) {
        // ======================================= 创建 ========================================
        // 使用 NIO 方式

        // 堆内
        ByteBuffer nioHeapByteBuffer = ByteBuffer.allocate(1024);
        // 直接内存
        ByteBuffer nioDirectByteBuffer = ByteBuffer.allocateDirect(1024);

        // 使用 Netty 方式

        // 堆内
        ByteBuf nettyHeapByteBuffer = Unpooled.buffer(1024);
        // 直接内存
        ByteBuf nettyDirectByteBuffer = Unpooled.directBuffer(1024);

        // ======================================= 读、写操作簇 ========================================
        // TODO 略

        // ======================================= 重用缓冲区 ========================================
        nettyHeapByteBuffer.discardReadBytes();
        nettyHeapByteBuffer.discardSomeReadBytes();


    }
}
