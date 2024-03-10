package com.v2v.courier.config;

import com.v2v.courier.service.JwtService;
import com.v2v.courier.service.UserDetailsServiceImplementation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsServiceImplementation userDetailsServiceImplementation;

  public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImplementation userDetailsServiceImplementation){
    this.jwtService = jwtService;
    this.userDetailsServiceImplementation = userDetailsServiceImplementation;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException{
    String authHeader = request.getHeader("Authorization");
    if ( authHeader == null || !authHeader.startsWith("Bearer ") ) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = authHeader.substring(7);
    String username = jwtService.extractUsername(token);

    if ( username != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
      UserDetails user = userDetailsServiceImplementation.loadUserByUsername(username);
      if ( jwtService.isValid(token, user) ) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
