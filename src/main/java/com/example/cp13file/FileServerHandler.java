package com.example.cp13file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-27 17:51
 **/
public class FileServerHandler extends SimpleChannelInboundHandler<String> {

    private static final String CR = System.getProperty("line.separator");

    @Override
    public void messageReceived(ChannelHandlerContext ctx, String msg)
            throws Exception {
        File file = new File(msg);
        if (file.exists()) {
            if (!file.isFile()) {
                ctx.writeAndFlush("Not a file : " + file + CR);
                return;
            }
            ctx.write(file + " " + file.length() + CR);
            RandomAccessFile randomAccessFile = new RandomAccessFile(msg, "r");
            FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());
            ctx.write(region);
            ctx.writeAndFlush(CR);
            randomAccessFile.close();
        } else {
            ctx.writeAndFlush("File not found: " + file + CR);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
