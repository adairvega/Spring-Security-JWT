package com.utez.sda.AppSecurityJPAJWT.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.utez.sda.AppSecurityJPAJWT.security.service.MiDetalleUsuarioService;

public class JwtTokenFilter extends OncePerRequestFilter{
   
   @Autowired
   JwtProvider jwtProvider;
   
   @Autowired
   MiDetalleUsuarioService userDetailService;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {
      try {
         String header = request.getHeader("Authorization");
         String token = null;
         if (header != null && header.startsWith("Bearer")) {
            token = header.replace("Bearer ", "");
         }
         
         if (token != null && jwtProvider.validarToken(token)) {
            String nombreUsuario = jwtProvider.getNombreUsuario(token);
            UserDetails userDetail = userDetailService.loadUserByUsername(nombreUsuario);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(nombreUsuario, null, userDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
         }         
      } catch (Exception e) {
         System.out.println("Error en el filtro");
      }
      filterChain.doFilter(request, response);
      
   }

}
