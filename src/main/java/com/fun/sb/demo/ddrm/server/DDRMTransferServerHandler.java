package com.fun.sb.demo.ddrm.server;

import com.baidu.nuomi.crm.ddrm.model.DDRMRequest;
import com.baidu.nuomi.crm.ddrm.model.DDRMResult;
import com.baidu.nuomi.crm.ddrm.server.service.DDRMService;
import com.baidu.nuomi.crm.ddrm.server.service.impl.DDRMServiceImpl;
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
        DDRMRequest request = (DDRMRequest) msg;
        GlobalSession.addDomainMap(request.getDomain(), ctx.channel());
        DDRMResult message = ddrmService.queryDomainProperties(request.getDomain());
        ctx.writeAndFlush(message);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.log(Level.WARNING, "Unexpected exception from downstream.",
                cause);
        ctx.close();
    }

}