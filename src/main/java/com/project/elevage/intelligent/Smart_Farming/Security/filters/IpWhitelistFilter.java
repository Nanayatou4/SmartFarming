package com.project.elevage.intelligent.Smart_Farming.Security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class IpWhitelistFilter extends OncePerRequestFilter {
    private static final List<String> ALLOWED_IPS = List.of("192.168.1.100");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientIp = request.getRemoteAddr();
        if (!ALLOWED_IPS.contains(clientIp)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
