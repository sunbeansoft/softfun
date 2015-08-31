package com.fun.sb.demo.ddrm.server;

import com.fun.sb.demo.ddrm.model.DDRMServiceResult;
import com.fun.sb.demo.ddrm.server.service.DDRMService;
import com.fun.sb.demo.ddrm.server.service.impl.DDRMServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DDRMTransferServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(DDRMTransferServerHandler.class.getName());

    private DDRMService ddrmService = new DDRMServiceImpl();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println(msg);
        DDRMServiceResult message = ddrmService.operateClientRequest(msg, ctx.channel());
        if (message.isSuccess() && message.isNeedWrite()) {
            ctx.writeAndFlush(message);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.log(Level.WARNING, "Unexpected exception from downstream.",
                cause);
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (!GlobalSession.dropChannel(ctx.channel())) {
            logger.log(Level.WARNING, "删除失败");
        }
        DDRMServiceResult message = ddrmService.dropChannel(ctx.channel());
        if (message.isSuccess() && message.isNeedWrite()) {
            ctx.writeAndFlush(message);
        }
        super.channelInactive(ctx);
    }

}