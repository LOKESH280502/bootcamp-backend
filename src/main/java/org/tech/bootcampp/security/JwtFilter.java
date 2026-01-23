package org.tech.bootcampp.security;

 
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
     
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        System.out.println("JWT FILTER PATH = " + request.getServletPath());
//        return false;
//    }
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        String path = request.getServletPath();
////        return false;
//        return path.equals("/auth/login") || path.equals("/auth/register");
////        return path.startsWith("/auth/");
//    }
    
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/auth/");
    }


    @Override
    protected void doFilterInternal(
    		
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {
    	System.out.println("JWT FILTER HIT: " + request.getMethod() + " " + request.getServletPath());

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            try {
                String token = header.substring(7);
                Claims claims = jwtUtil.extractClaims(token);

                String email = claims.getSubject();
                String role = claims.get("role", String.class);
                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority("ROLE_"+role.toUpperCase());

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(authority)
                        );
//                System.out.println("JWT ROLE = " + role);
//                System.out.println("AUTHORITY = " + authority.getAuthority());


                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("JWT ROLE = " + role);
                System.out.println("AUTHORITY = " + authority.getAuthority());


            } catch (Exception e) {
                // ❌ DO NOT THROW
            	 e.printStackTrace();
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);
    }

}

