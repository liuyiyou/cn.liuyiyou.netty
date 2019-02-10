package cn.liuyiyou.netty.definitive.guide.chapter4;

import cn.liuyiyou.netty.definitive.guide.chapter3.RISC;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: liuyiyou.cn
 * @date: 2019/1/29
 * @version: V1.0
 */
@Slf4j
public class TimeClientHandler extends ChannelInboundHandlerAdapter {


    private byte[] req;
    private int counter;


    public TimeClientHandler() {
        req = (RISC.QUERY_TIME_ORDER + System.getProperty("line.separator")).getBytes();
    }


    /**
     * 当客户端和服务端TCP链路建立的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer();
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    /**
     * 服务端返回应答消息调用 channelRead
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("接收服务端返回来的应答信息");
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        log.info("现在时刻:" + body + "; the counter is : " + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("发生异常。。。。。。。", cause);
        ctx.close();
    }
}
