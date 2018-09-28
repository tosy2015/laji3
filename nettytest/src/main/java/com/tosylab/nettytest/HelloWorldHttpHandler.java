package com.tosylab.nettytest;

import com.mashape.unirest.http.Unirest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HelloWorldHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (msg instanceof LastHttpContent) {
//            ByteBuf data = msg.content();
//            if(null != data && data.readableBytes() > 0){
//                TelegramModel obj = JSONObject.parseObject(data.toString(),TelegramModel.class);
//                if (null != obj && null != obj){
                    //send
//                    int chat_id = 672868707;
//                    String url = String.format("https://api.telegram.org/bot580249700:AAGSAE0bewvzOMd4vZMCy3TkjkXxPTSYxvk/sendMessage?text=%s&chat_id=%d", "hello",chat_id);
//                    Unirest.get(url).asJsonAsync();
//
//            Unirest.get("https://api.telegram.org/bot580249700:AAGSAE0bewvzOMd4vZMCy3TkjkXxPTSYxvk/sendMessage?text=hello&chat_id=672868707").asJson();

//                }
//            }

            ByteBuf content = Unpooled.copiedBuffer("{\"Hello!\",\"HELLO!\"}", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
            ctx.close();
        }
    }
}
