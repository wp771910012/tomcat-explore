package com.wp.tomcat.explore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import com.wp.tomcat.explore.connector.http.HttpRequest;
import com.wp.tomcat.explore.connector.http.HttpResponse;

/**
 * @Description
 * @Author wangpeng
 * @Date 2019/5/7 13:53
 */
public class HttpServer {

    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    // the shutdown command received
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        // Loop waiting for a request
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();
                // create HttpRequest object and parse
                HttpRequest httpRequest = new HttpRequest(input);
                httpRequest.parse();
                // create HttpResponse object
                HttpResponse response = new HttpResponse(output);
                response.setHttpRequest(httpRequest);
                //区别静态资源请求和servlet请求
                if (httpRequest.getUri().startsWith("/servlet/")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(httpRequest, response);
                } else {
                    StaticResourceProcessor processor =
                            new StaticResourceProcessor();
                    processor.process(httpRequest, response);
                }
                // Close the socket
                socket.close();
                //check if the previous URI is a shutdown command
                shutdown = httpRequest.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
