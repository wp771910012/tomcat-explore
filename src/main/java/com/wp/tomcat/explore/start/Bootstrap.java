package com.wp.tomcat.explore.start;

import com.wp.tomcat.explore.connector.http.HttpConnector;

/**
 * @Description 启动类
 * @Author wangpeng
 * @Date 2019/5/16 14:08
 */
public class Bootstrap {

    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }

}
