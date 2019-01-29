package cn.liuyiyou.netty.definitive.guide.chapter3;

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


    private final ByteBuf firstMessage;

    public TimeClientHandler() {
        byte[] order = RISC.QUERY_TIME.getBytes();
        firstMessage = Unpooled.buffer(order.length);
        firstMessage.writeBytes(order);
    }


    /**
     * 当客户端和服务端TCP链路建立的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("与服务端建立连接。。。。。。");
        log.info("发送指令集");
        ctx.writeAndFlush(firstMessage);
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
        log.info("现在时刻:" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("发生异常。。。。。。。", cause);
        ctx.close();
    }
}
