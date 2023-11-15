package org.example.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.example.response.GptResponse;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;

/**
 * @author mingyuan 2023/11/14
 */
public class GptClientUtils {

    static Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7820));

    public static GptResponse execute(Map<String, String> head, String body, String url) {
        HttpRequest httpRequest = HttpRequest.post(url)
                .setProxy(proxy)
                .header("Content-Type", "application/json")
                .body(body);
        head.forEach(httpRequest::header);
        return JSONObject.parseObject(httpRequest.execute().body(), GptResponse.class);
    }
}
