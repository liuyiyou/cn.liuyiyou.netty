//package cn.liuyiyou.netty.definitive.guide.chapter10;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.http.HttpObjectAggregator;
//import io.netty.handler.codec.http.HttpRequestDecoder;
//import io.netty.handler.codec.http.HttpResponseEncoder;
//import io.netty.handler.stream.ChunkedWriteHandler;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author: liuyiyou.cn
// * @date: 2019/1/29
// * @version: V1.0
// */
//@Slf4j
//public class HttpFileServer {
//
//
//    private static final String DEFAULT_URL = "/src/cn.liuyiyou";
//
//    public static void main(String[] args) throws InterruptedException {
//        new HttpFileServer().run(8080,DEFAULT_URL);
//    }
//
//    public void run(final int port, final String url) throws InterruptedException {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            ServerBootstrap serverBootstrap = new ServerBootstrap();
//            serverBootstrap.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
//                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
//                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
//                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
//                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
//                        }
//                    });
//            ChannelFuture channelFuture = serverBootstrap.bind("127.0.0.1", port).sync();
//            log.info("Http文件目录服务器启动，网址是：http://127.0.0.1" + port + url);
//            channelFuture.channel().closeFuture().sync();
//        } finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
//    }
//}
