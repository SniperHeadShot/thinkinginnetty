package com.bat.netty.customprotocol.util;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * Jboss Marshalling 编解码器
 *
 * @author ZhengYu
 * @version 1.0 2020/8/29 9:41
 **/
public class MarshallingUtil {

    /**
     * 创建Jboss Marshalling编码器
     *
     * @author ZhengYu
     */
    public static MarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        return new MarshallingEncoder(provider);
    }


    /**
     * 创建Jboss Marshalling解码器
     *
     * @author ZhengYu
     */
    public static MarshallingDecoder buildMarshallingDecoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
        return new MarshallingDecoder(provider, 1024);
    }
}
