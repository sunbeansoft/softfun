package com.fun.sb.demo.ddrm.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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

    /**
     * 通过channel查找所属的domain，便于删除
     */
    private static Map<Channel, String> channelDomainMap = Maps.newConcurrentMap();

    public static Map<String, List<Channel>> getDomainMap() {
        return domainMap;
    }

    /**
     * 获取一个domain下面的所有连接
     *
     * @param domain
     * @return
     */
    public static List<Channel> getDomainContext(String domain) {
        return domainMap.get(domain);
    }

    /**
     * 添加channel并通过domain分类
     *
     * @param domain  域名称 每个域代表一个服务
     * @param channel channel
     */
    public static void addDomainMap(String domain, Channel channel) {
        List<Channel> contexts = domainMap.get(domain);
        if (CollectionUtils.isEmpty(contexts)) {
            contexts = Lists.newArrayList();
            contexts.add(channel);
            domainMap.put(domain, contexts);
        } else {
            contexts.add(channel);
        }
        addChannelDomainMap(domain, channel);
    }

    /**
     * 便于查找
     *
     * @param domain  域名称 每个域代表一个服务
     * @param channel channel
     */
    private static void addChannelDomainMap(String domain, Channel channel) {
        channelDomainMap.put(channel, domain);
    }

    /**
     * 当客户端断开时使用
     *
     * @param channel 连接
     */
    public static boolean dropChannel(Channel channel) {
        if (channel == null) {
            return false;
        }
        String domain = channelDomainMap.get(channel);
        if (StringUtils.isBlank(domain)) {
            return false;
        }
        List<Channel> channels = domainMap.get(domain);
        if (CollectionUtils.isEmpty(channels)) {
            return false;
        }
        if (channels.remove(channel)) {//核心
            String removeDomain = channelDomainMap.remove(channel);
            if (StringUtils.isNotBlank(domain)) {
                return true;
            }
        }
        return false;
    }

}
