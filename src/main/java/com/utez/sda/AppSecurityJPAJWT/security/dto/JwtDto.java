package com.utez.sda.AppSecurityJPAJWT.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtDto {
   
   private String token;
   private String bearer = "Bearer";
   private String nombreUusario;
   private Collection<? extends GrantedAuthority> authorities;
   public JwtDto(String token, String nombreUusario, Collection<? extends GrantedAuthority> authorities) {
      
      this.token = token;
      this.nombreUusario = nombreUusario;
      this.authorities = authorities;
   }
   public String getToken() {
      return token;
   }
   public void setToken(String token) {
      this.token = token;
   }
   public String getBearer() {
      return bearer;
   }
   public void setBearer(String bearer) {
      this.bearer = bearer;
   }
   public String getNombreUusario() {
      return nombreUusario;
   }
   public void setNombreUusario(String nombreUusario) {
      this.nombreUusario = nombreUusario;
   }
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
   }
   public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
      this.authorities = authorities;
   }
}
