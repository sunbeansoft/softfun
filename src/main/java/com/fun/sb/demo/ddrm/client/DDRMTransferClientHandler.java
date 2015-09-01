package com.fun.sb.demo.ddrm.client;

import com.fun.sb.demo.ddrm.DistributeDataResourceManager;
import com.fun.sb.demo.ddrm.model.DDRMResult;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DDRMTransferClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(DDRMTransferClientHandler.class.getName());

    private DistributeDataResourceManager manager;

    public DDRMTransferClientHandler(DistributeDataResourceManager manager) {
        this.manager = manager;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send the message to Server
        super.channelActive(ctx);
        manager.setChannel(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // you can use the Object from Server here
        System.out.println("receive from server:" + msg);
        DDRMResult result = (DDRMResult) msg;
        manager.setNewValue(result);
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
        ctx.fireChannelInactive();
    }

}