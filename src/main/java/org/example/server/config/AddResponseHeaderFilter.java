package org.example.server.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AddResponseHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
//        httpServletResponse.addHeader("X-Frame-Options", "DENY");
//        httpServletResponse.addHeader("Cache-Control", "no-cache, no-store, must-revalidate, max-age=0");
        httpServletResponse.addHeader("Cache-Control", "max-age=600");
//        httpServletResponse.addHeader("Expires", "Fri, 17 May 2024 16:50:01 GMT");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}