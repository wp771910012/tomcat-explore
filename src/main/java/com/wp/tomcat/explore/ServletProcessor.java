package com.wp.tomcat.explore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import javax.servlet.Servlet;
import com.wp.tomcat.explore.connector.http.Constants;
import com.wp.tomcat.explore.connector.http.HttpRequest;
import com.wp.tomcat.explore.connector.http.HttpResponse;

/**
 * @Description
 * @Author wangpeng
 * @Date 2019/5/8 16:31
 */
public class ServletProcessor {

    public void process(HttpRequest httpRequest, HttpResponse response) {
        String uri = httpRequest.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            // create a URLClassLoader
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.SERVLET_ROOT);
            // the forming of repository is taken from the
            // createClassLoader method in
            // org.apache.catalina.startup.ClassLoaderFactory
            String repository =
                    (new URL("file", null, classPath.getCanonicalPath() +
                            File.separator)).toString();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        Class myClass = null;
        try {
            myClass = Class.forName(HttpServer.class.getPackage().getName() + "." + servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        Servlet servlet = null;
        try {
            RequestFaced requestFaced = new RequestFaced(httpRequest);
            ResponseFaced responseFaced = new ResponseFaced(response);
            servlet = (Servlet) myClass.newInstance();
            servlet.service(requestFaced, responseFaced);
        } catch (Exception e) {
            System.out.println(e.toString());
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
    }

    private void parseRequest(InputStream inputStream, OutputStream outputStream) {
        // Read a set of characters from the socket
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        String[] lines = request.toString().split("\n");
        String[] firstLines = lines[0].split(" ");
        String method = firstLines[0];
        String uri = firstLines[1];
        String protocol = firstLines[2];
    }
}
