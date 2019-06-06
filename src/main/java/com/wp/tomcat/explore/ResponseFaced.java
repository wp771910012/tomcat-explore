package com.wp.tomcat.explore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

/**
 * @Description
 * @Author wangpeng
 * @Date 2019/5/14 9:48
 */
public class ResponseFaced implements ServletResponse {

    private ServletResponse servletResponse;

    public ResponseFaced(ServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    public String getCharacterEncoding() {
        return this.servletResponse.getCharacterEncoding();
    }

    public String getContentType() {
        return this.servletResponse.getContentType();
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return this.getOutputStream();
    }

    public PrintWriter getWriter() throws IOException {
        return this.servletResponse.getWriter();
    }

    public void setCharacterEncoding(String s) {
        this.servletResponse.setCharacterEncoding(s);
    }

    public void setContentLength(int i) {
        this.servletResponse.setContentLength(i);
    }

    public void setContentLengthLong(long l) {
        this.servletResponse.setContentLengthLong(l);
    }

    public void setContentType(String s) {
        this.servletResponse.setContentType(s);
    }

    public void setBufferSize(int i) {
        this.servletResponse.setBufferSize(i);
    }

    public int getBufferSize() {
        return this.servletResponse.getBufferSize();
    }

    public void flushBuffer() throws IOException {
        this.servletResponse.flushBuffer();
    }

    public void resetBuffer() {
        this.servletResponse.resetBuffer();
    }

    public boolean isCommitted() {
        return this.servletResponse.isCommitted();
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {
        return null;
    }
}
