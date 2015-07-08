package com.fun.sb.demo.eventbus;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

import java.util.List;
import java.util.Map;

public class DeadEventListener {
    boolean notDelivered = false;

    @Subscribe
    public void listen(DeadEvent event) {
        notDelivered = true;
    }

    public boolean isNotDelivered() {
        return notDelivered;
    }


    public static void main(String[] args) {
        Map<String, Map<Long, List<String>>> map = Maps.newHashMap();
        System.out.println(map);
    }
}