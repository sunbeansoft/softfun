package com.fun.sb.demo.ddrm.server;

import com.fun.sb.demo.ddrm.model.DDRMRequest;
import com.sun.corba.se.pept.encoding.OutputObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

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
