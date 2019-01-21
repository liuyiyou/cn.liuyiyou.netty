package cn.liuyiyou.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: liuyiyou.cn
 * @date: 2019/1/21
 * @version: V1.0
 */
public class HelloServer {

    private int port;

    public HelloServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        int port = 10110;
        new HelloServer(port).run();
    }

    public void run() throws InterruptedException {
        //初始化用于Acceptor的主"线程池"以及用于I/O工作的从"线程池"；
        EventLoopGroup bossGroup = new NioEventLoopGroup();//（1）
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("准备运行端口：" + port);
        try {
            //初始化ServerBootstrap实例， 此实例是netty服务端应用开发的入口
            ServerBootstrap b = new ServerBootstrap(); //(2)
            //通过ServerBootstrap的group方法，设置（1）中初始化的主从"线程池"；
            b.group(bossGroup, workerGroup)// (3)
                    //指定通道channel的类型，由于是服务端，故而是NioServerSocketChannel；
                    .channel(NioServerSocketChannel.class) // (4)
                    //设置ServerSocketChannel的处理器
                    .handler(new LoggingHandler())    // (5)
                    //设置子通道也就是SocketChannel的处理器， 其内部是实际业务开发的"主战场"
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (6)
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HelloServerHandler());
                        }
                    })
                    //配置ServerSocketChannel的选项
                    .option(ChannelOption.SO_BACKLOG, 128)          // (7)
                    // 配置子通道也就是SocketChannel的选项
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (8)
            // 绑定并侦听某个端口
            ChannelFuture f = b.bind(port).sync(); // (9)
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
