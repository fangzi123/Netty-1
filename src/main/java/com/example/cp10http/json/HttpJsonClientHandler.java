package com.example.cp10http.json;

import com.example.cp10http.json.codec.HttpJsonRequest;
import com.example.cp10http.json.codec.HttpJsonResponse;
import com.example.cp10http.xml.pojo.OrderFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-27 11:19
 **/
public class HttpJsonClientHandler extends SimpleChannelInboundHandler<HttpJsonResponse> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        HttpJsonRequest request = new HttpJsonRequest(null, OrderFactory.create(123));
        ctx.writeAndFlush(request);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx,
                                   HttpJsonResponse msg) throws Exception {
        System.out.println("The client receive response of http header is : " + msg.getHttpResponse().headers().names());
        System.out.println("The client receive response of http body is : " + msg.getResult());
    }
}
