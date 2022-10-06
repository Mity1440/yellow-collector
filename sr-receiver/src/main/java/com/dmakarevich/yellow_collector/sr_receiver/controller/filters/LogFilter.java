package com.dmakarevich.yellow_collector.sr_receiver.controller.filters;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("{}  {}  {}  {} {} {}",
                 request.getMethod(),
                 request.getRemoteAddr(),
                 request.getServerPort(),
                 request.getLocalPort(),
                 request.getRequestURI(),
                 request.getContentType());

        filterChain.doFilter(request, response);

    }


}
