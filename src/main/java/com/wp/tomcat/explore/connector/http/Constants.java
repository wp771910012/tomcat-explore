package com.wp.tomcat.explore.connector.http;

import com.wp.tomcat.explore.HttpServer;

/**
 * @Description
 * @Author wangpeng
 * @Date 2019/5/8 16:33
 */
public class Constants {

    public static final String Package = "com.wp.tomcat.explore";
    /**
     * WEB_ROOT is the directory where our HTML and other files reside. For this package, WEB_ROOT is the "webroot"
     * directory under the working directory. The working directory is the location in the file system from where the
     * java command was invoked.
     */
    public static final String WEB_ROOT = HttpServer.class.getResource("/").getPath() + "webroot";

    public static final String SERVLET_ROOT = HttpServer.class.getResource("").getPath();

    public static final String  SUCCESS_MESSAGE = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n";

    public static final String SUCCESS_JSON = "HTTP/1.1 200 \r\n" + "Content-Type: application/json;charset=UTF-8\r\n\n";
}
