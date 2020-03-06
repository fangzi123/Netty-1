package com.example.cp10http.json.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;

import java.util.List;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-27 11:09
 **/
public class HttpJsonResponseDecoder extends AbstractHttpJsonDecoder<DefaultFullHttpResponse> {
    public HttpJsonResponseDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    public HttpJsonResponseDecoder(Class<?> clazz, boolean isPrintlog) {
        super(clazz, isPrintlog);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          DefaultFullHttpResponse msg, List<Object> out) throws Exception {
        HttpJsonResponse httpJsonResponse = new HttpJsonResponse(msg, decode0(
                ctx, msg.content()));
        out.add(httpJsonResponse);
    }

}
