package com.fun.sb.demo.http.fetcher;

import com.fun.sb.demo.http.parser.ResultParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 远程资源抓取器
 * User: shangbin01
 * Date: 16/4/25
 * Time: 下午4:59
 */
public abstract class RemoteResourceFetcher implements ResourceFetcher {

    private static Logger logger = LoggerFactory.getLogger(RemoteResourceFetcher.class);

    /**
     * 执行上下文
     */
    private static HttpClientContext context = HttpClientContext.create();

    /**
     * 执行正确
     */
    private static final int EXECUTE_SUCCESS = 200;

    public <T> T fetch(HttpRequestBase method, ResultParser<T> parser) {
        try {
            CloseableHttpResponse response = getHttpClient().execute(method, context);
            if (response.getStatusLine().getStatusCode() == EXECUTE_SUCCESS) {
                try {
                    HttpEntity entity = response.getEntity();
                    return parser.handleResult(EntityUtils.toString(entity));
                } finally {
                    response.close();
                }
            }
            parser.handleError(response.getStatusLine());
            logger.error("远程http返回错误:" + method.getURI().getHost() + ":" + method.getURI().getPort() + "?" + method.getURI().getPath());
        } catch (ClientProtocolException ex) {
            parser.handleException(ex);
            logger.error("执行错误", ex);
        } catch (IOException ex) {
            parser.handleException(ex);
            logger.error("io异常", ex);
        }
        return null;
    }

    /**
     * 获取httpclient
     *
     * @return
     */
    public abstract CloseableHttpClient getHttpClient();
}
