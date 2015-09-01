package com.fun.sb.demo.ddrm.server;

/**
 * Created by sunbeansoft on 15-9-1.
 */
public class Bootstrap {

    private Thread server = new Thread(new DDRMServer());

    public Bootstrap() {
        server.start();
    }

    public static void main(String[] args) {
        new Bootstrap();
    }

}
