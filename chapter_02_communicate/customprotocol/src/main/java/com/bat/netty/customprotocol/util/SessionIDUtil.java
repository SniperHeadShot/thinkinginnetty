package com.bat.netty.customprotocol.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 唯一会话ID生成器
 *
 * @author ZhengYu
 * @version 1.0 2020/8/30 17:51
 **/
public class SessionIDUtil {

    private static final AtomicLong ATOMIC_LONG = new AtomicLong();

    public static Long buildSessionID() {
        return ATOMIC_LONG.incrementAndGet();
    }
}
