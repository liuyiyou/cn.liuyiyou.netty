//package cn.liuyiyou.netty.definitive.guide.chapter10;
//
//import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;
//import io.netty.channel.ChannelHandler;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.codec.http.FullHttpMessage;
//import io.netty.handler.codec.http.FullHttpRequest;
//import io.netty.handler.codec.http.HttpMethod;
//
//import java.io.File;
//
//import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
//import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
//import static io.netty.handler.codec.http.HttpResponseStatus.METHOD_NOT_ALLOWED;
//
///**
// * @author: liuyiyou.cn
// * @date: 2019/1/29
// * @version: V1.0
// */
//public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
//        if(!request.getDecoderResult().isSuccess()){
//            sendError(ctx,BAD_REQUEST);
//            return;
//        }
//        if(request.getMethod()!= HttpMethod.GET){
//            sendError(ctx,METHOD_NOT_ALLOWED);
//            return;
//        }
//        final String uri = request.getUri();
//        final String path = sanitzeUri(uri);
//        if(path==null){
//            sendError(ctx,FORBIDDEN);
//            return;
//        }
//
//        File file = new File(path);
//        if()
//    }
//}
