package com.beaudoin.circleapi.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.core.userdetails.User;

import com.beaudoin.circleapi.utility.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        System.out.println("About to authenticate...");
        Authentication authentication = getAuthentication(request);


        
        System.out.println("User is authenticated: " + authentication.isAuthenticated());
        System.out.println("Authenticated!");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("Authentication has been set!");

        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        System.out.println("Inside getAuthentication");
        String token = request.getHeader("Authorization");
        System.out.println("Inside authentication, token is: " + token);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (JwtUtil.validateToken(token, JwtUtil.secret)) {
                String email = JwtUtil.extractFieldFromJWT(token, "email");
                List<String> roles = JwtUtil.extractAuthorities(token);

                UserDetails userDetails = User.builder()
                        .username(email)
                        .authorities(roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                        .password("") // No password is stored in the token
                        .build();

                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }
        }

        return null;
    }
}

