package com.fun.sb.demo.ddrm.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by sunbeansoft on 15-8-30.
 */
public class GlobalSession {
    /**
     * 服务端向客户端推送数据使用
     */
    private static Map<String, List<Channel>> domainMap = Maps.newConcurrentMap();

    public static Map<String, List<Channel>> getDomainMap() {
        return domainMap;
    }

    public static List<Channel> getDomainContext(String domain) {
        return domainMap.get(domain);
    }

    public static void addDomainMap(String domain, Channel context) {
        List<Channel> contexts = domainMap.get(domain);
        if (CollectionUtils.isEmpty(contexts)) {
            contexts = Lists.newArrayList();
            contexts.add(context);
            domainMap.put(domain, contexts);
        } else {
            contexts.add(context);
        }
    }
}
