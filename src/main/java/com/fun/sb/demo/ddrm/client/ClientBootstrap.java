package com.fun.sb.demo.ddrm.client;

import com.fun.sb.demo.ddrm.model.DemoBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sunbeansoft on 15-9-23.
 */
public class ClientBootstrap {


    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/spring.xml");

        DemoBean bean = (DemoBean) context.getBean("demoBean");
        System.out.println(bean.getA());
    }
}
