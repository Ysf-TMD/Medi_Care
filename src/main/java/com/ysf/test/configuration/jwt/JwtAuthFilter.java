package com.ysf.test.configuration.jwt;

import com.ysf.test.configuration.jwt.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request , HttpServletResponse response , FilterChain chain )
    throws ServletException , IOException
    {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null || authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return ;
        }

        String token = authHeader.substring(7);
        String username = jwtService.extraireUsername(token) ;

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userDetailsService.loadUserByUsername(username);
            if(jwtService.estValid(token , user )){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username , user.getAuthorities()) ;
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
