package com.fun.sb.demo.ddrm.server;

import com.fun.sb.demo.ddrm.model.DDRMResult;
import com.fun.sb.demo.ddrm.model.DDRMServiceResult;
import com.fun.sb.demo.ddrm.server.service.DDRMService;
import com.fun.sb.demo.ddrm.server.service.impl.DDRMServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DDRMTransferServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(DDRMTransferServerHandler.class.getName());

    private DDRMService ddrmService = new DDRMServiceImpl();

    private Thread sech = new Thread(new Runnable() {
        @Override
        public void run() {
            DDRMResult message = ddrmService.queryDomainProperties("crm");
            ddrmService.pushPropertiesToDomain("crm", message);
        }
    });

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("receive from client:" + msg);
        DDRMServiceResult message = ddrmService.operateClientRequest(msg, ctx.channel());
        if (message.isSuccess() && message.isNeedWrite()) {
            ctx.writeAndFlush(message.getResult());
            new ScheduledThreadPoolExecutor(1).scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    DDRMResult message = ddrmService.queryDomainProperties("crm");
                    ddrmService.pushPropertiesToDomain("crm", message);
                }
            }, 10000, 5000, TimeUnit.MILLISECONDS);
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