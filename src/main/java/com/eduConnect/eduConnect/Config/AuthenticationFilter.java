package com.eduConnect.eduConnect.Config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends GenericFilterBean {

    private static final List<String> AUTH_WHITELIST = Arrays.asList(
        "/api/v1/**", "/api/v1/admin/**", "/api/v1/admin/details", "/api/v1/user/**", "/api/v1/user/details", "/api/v1/admin/delete/{userId}", "/api/v1/admin/login","/api/v1/user/reg","/api/v1/paypal/create-payment","/api/v1/paypal/cancel-payment","/api/v1/paypal/execute-payment","/favicon.ico", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/swagger-ui.html","/swagger-ui.html#/"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Check if the request URI matches any whitelisted path
        String requestURI = httpRequest.getRequestURI();
        if (isWhitelisted(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = AuthenticationService.getAuthentication(httpRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exp) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = httpResponse.getWriter();
            writer.print(exp.getMessage());
            writer.flush();
            writer.close();
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isWhitelisted(String requestURI) {
        for (String path : AUTH_WHITELIST) {
            if (requestURI.startsWith(path)) {
                return true;
            }
        }
        return false;
    }
}
