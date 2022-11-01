package com.example.bookstore.demo.security;

import com.example.bookstore.demo.config.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenUtil tokenUtil;
    private CustomUserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenUtil tokenUtil, UserDetailsService userDetailsService){
        this.tokenUtil = tokenUtil;
        this.userDetailsService = (CustomUserDetailsService) userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
