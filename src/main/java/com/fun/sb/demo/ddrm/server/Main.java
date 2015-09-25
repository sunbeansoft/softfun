package com.fun.sb.demo.ddrm.server;

import com.baidu.nuomi.crm.ddrm.model.DDRMRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by sunbeansoft on 15-9-8.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        DDRMRequest req = new DDRMRequest();
        req.setDomain("abc");
        req.setIp("12");

        ObjectOutputStream obj = new ObjectOutputStream(System.out);
        obj.writeObject(req);

    }
}
