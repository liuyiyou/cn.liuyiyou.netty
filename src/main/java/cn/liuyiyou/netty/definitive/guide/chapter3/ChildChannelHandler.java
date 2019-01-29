package cn.liuyiyou.netty.definitive.guide.chapter3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author: liuyiyou.cn
 * @date: 2019/1/29
 * @version: V1.0
 */
public class ChildChannelHandler extends ChannelInitializer {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new TimeServerHandler());
    }
}
