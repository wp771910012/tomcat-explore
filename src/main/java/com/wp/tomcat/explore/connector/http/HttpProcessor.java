package com.wp.tomcat.explore.connector.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.servlet.ServletException;

/**
 * @Description
 * @Author wangpeng
 * @Date 2019/5/20 9:33
 */
public class HttpProcessor {

    /**
     * The HttpConnector with which this processor is associated.
     */
    private HttpConnector connector = null;
    private HttpRequest request;
    private HttpRequestLine requestLine = new HttpRequestLine();
    private HttpResponse response;

    protected String method = null;
    protected String queryString = null;

    private HttpRequest httpRequest;

    public void process(Socket socket) {
        InputStream inputStream;
        OutputStream outputStream;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            httpRequest = new HttpRequest(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parsRequest(SocketInputStream inputStream, OutputStream outputStream) throws IOException {
        inputStream.readRequestLine(requestLine);
        String method = new String(requestLine.method, 0, requestLine.methodEnd);
        String uri = null;
        String protocol = new String(requestLine.protocol, 0,
                requestLine.protocolEnd);// Validate the incoming request line
        if (method.length() < 1) {
            throw new ServletException("Missing HTTP request method");
        } else if (requestLine.uriEnd < 1) {
            throw new ServletException("Missing HTTP request URI");
        }
// Parse any query parameters out of the request URI
        int question = requestLine.indexOf("?");
        if (question >= 0) {
            request.setQueryString(new String(requestLine.uri, question + 1,
                    requestLine.uriEnd - question - 1));
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }
// Checking for an absolute URI (with the HTTP protocol)
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
// Parsing out protocol and host name
            if (pos != -1) {
                pos = uri.indexOf('/', pos + 3);
                if (pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }
// Parse any requested session ID out of the request URI
        String match = ";jsessionid=";
        int semicolon = uri.indexOf(match);
        if (semicolon >= 0) {
            String rest = uri.substring(semicolon + match, length());
            int semicolon2 = rest.indexOf(';');
            if (semicolon2 >= 0) {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            } else {
                request.setRequestedSessionId(rest);
                rest = "";
            }
            request.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
        } else {
            request.setRequestedSessionId(null);
            request.setRequestedSessionURL(false);
        }
// Normalize URI (using String operations at the moment)
        String normalizedUri = normalize(uri);
// Set the corresponding request properties
        ((HttpRequest) request).setMethod(method);
        request.setProtocol(protocol);
        if (normalizedUri != null) {
            ((HttpRequest) request).setRequestURI(normalizedUri);
        } else {
            ((HttpRequest) request).setRequestURI(uri);
        }
        if (normalizedUri == null) {
            throw new ServletException("Invalid URI: " + uri + "'");
        }
    }

}
