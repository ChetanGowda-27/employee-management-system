package com.chetangowda.ems.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class HttpMethodFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String method = httpRequest.getParameter("_method");
        if (method != null && (method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("DELETE"))) {
            chain.doFilter(new HttpMethodRequestWrapper(httpRequest, method), response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}

    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method.toUpperCase();
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }
}