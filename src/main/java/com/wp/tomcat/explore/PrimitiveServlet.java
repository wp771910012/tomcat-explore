package com.wp.tomcat.explore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.alibaba.fastjson.JSON;
import com.wp.tomcat.explore.connector.http.Constants;

/**
 * @Description
 * @Author wangpeng
 * @Date 2019/5/8 9:36
 */
public class PrimitiveServlet implements Servlet {

    public void init(ServletConfig config) {
        System.out.println("init");
    }

    public void service(ServletRequest request, ServletResponse response)
            throws IOException {
        System.out.println("from service");
        PrintWriter out = response.getWriter();
        out.print(Constants.SUCCESS_JSON);
        System.out.print(Constants.SUCCESS_JSON);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("message", "qqwdqwdq");
        out.println(JSON.toJSONString(resultMap));
        System.out.println(JSON.toJSONString(resultMap));
    }

    public void destroy() {
        System.out.println("destroy");
    }

    public String getServletInfo() {
        return null;
    }

    public ServletConfig getServletConfig() {
        return null;
    }
}