package com.wp.tomcat.explore;

import java.io.IOException;
import com.wp.tomcat.explore.connector.http.HttpRequest;
import com.wp.tomcat.explore.connector.http.HttpResponse;

/**
 * @Description
 * @Author wangpeng
 * @Date 2019/5/8 16:27
 */
public class StaticResourceProcessor {
    public void process(HttpRequest httpRequest, HttpResponse response) {
        try {
            response.sendStaticResource();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
