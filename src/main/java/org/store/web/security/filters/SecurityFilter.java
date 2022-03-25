package org.store.web.security.filters;

import lombok.AllArgsConstructor;
import org.store.service.SecurityService;
import org.store.web.entity.Session;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class SecurityFilter implements Filter {
    private final SecurityService securityService;
    private Session session;

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("doFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<String> allow = List.of("/products", "/static", "/login");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getValue());
        }

        System.out.println("session.getProductList " + session.getProductList());
        securityService.checkUserToken(session, cookies);

        for (String path : allow) {
            if (request.getRequestURI().startsWith(path)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        redirect(response);
    }

    private void redirect(HttpServletResponse response) {
        try {
            response.sendRedirect("/login");
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    public void destroy() {
    }
}