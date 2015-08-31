package com.fun.sb.demo.ddrm.client;

import com.fun.sb.demo.ddrm.model.DemoBean;
import com.fun.sb.demo.ddrm.DistributeDataResourceManager;
import com.fun.sb.demo.ddrm.model.DDRMRequest;
import com.fun.sb.demo.ddrm.model.DDRMResult;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DDRMTransferClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(DDRMTransferClientHandler.class.getName());

    //    private final DDRMResult message;
    private final DDRMRequest request;

    private DemoBean demoBean;

    private DistributeDataResourceManager manager = new DistributeDataResourceManager();

    /**
     * Creates a client-side handler.
     */
    public DDRMTransferClientHandler() {
        request = new DDRMRequest();
        request.setDomain("crm");
        demoBean = new DemoBean();
        try {
            manager.regist("crm", demoBean);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send the message to Server
        super.channelActive(ctx);
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // you can use the Object from Server here
        System.out.println(msg);
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
}