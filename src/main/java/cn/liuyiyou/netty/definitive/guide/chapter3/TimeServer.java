package cn.liuyiyou.netty.definitive.guide.chapter3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: liuyiyou.cn
 * @date: 2019/1/29
 * @version: V1.0
 */
@Slf4j
public class TimeServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new TimeServer().bind(port);
    }


    public void bind(int port) throws Exception {
        log.info("//配置服务端NIO线程组");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            log.info("//绑定端口，同步等待成功");
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            log.info("//等待服务端监听端口关闭");
            channelFuture.channel().closeFuture().sync();
        } finally {
            log.info(" //优雅退出");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
