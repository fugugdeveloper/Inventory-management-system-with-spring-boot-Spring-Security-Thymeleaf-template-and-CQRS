package com.ktun.inventory_management_system.config;

import com.ktun.inventory_management_system.exceptions.InvalidTokenException;
import com.ktun.inventory_management_system.exceptions.TokenExpiredException;
import com.ktun.inventory_management_system.helper.JwtUtil;
import com.ktun.inventory_management_system.service.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;


@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null) {
            response.sendRedirect("/login");
        }

        String jwtToken = token;
        String username = null;

        try {
            username = jwtUtil.extractUsername(jwtToken);
            System.out.println("===================================================================");
            System.out.println("jwtToken: " + jwtToken);
            System.out.println("===================================================================");
        } catch (ExpiredJwtException ex) {
            logger.warn("Token expired", ex);
            throw new TokenExpiredException("Token expired");
        } catch (JwtException ex) {
            logger.warn("Invalid token", ex);
           throw new InvalidTokenException("Invalid token");
        } catch (IllegalArgumentException ex) {
            logger.warn("JWTToken is null ", ex);
          throw new AuthenticationException("JWTToken is null") {};
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            System.out.println("username: " + userDetails.getUsername());
            System.out.println("password: " + userDetails.getPassword());
            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Token Validated: " + jwtToken);
            } else {
              throw new AuthenticationException("Invalid token"){};
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.equals("/login")
                || path.equals("/register")
                || path.startsWith("/static")
                || path.startsWith("/assets")
                || path.startsWith("/css")
                || path.startsWith("/js")
                || path.startsWith("/images")
                || path.startsWith("/favicon");
    }
}
