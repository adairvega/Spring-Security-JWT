package com.utez.sda.AppSecurityJPAJWT.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MiDetalleUsuario implements UserDetails{
   
   private String nombre;
   
   private String usuario;
   
   private String email;
   
   private String password;
   
   private Collection<? extends GrantedAuthority> authorities;

   public MiDetalleUsuario(String nombre, String usuario, String email, String password,
         Collection<? extends GrantedAuthority> authorities) {
      super();
      this.nombre = nombre;
      this.usuario = usuario;
      this.email = email;
      this.password = password;
      this.authorities = authorities;
   }
   
   public static MiDetalleUsuario construir(Usuario usuario) {
      List<GrantedAuthority> auths = usuario.getRoles()
                                          .stream()
                                          .map(
                                                rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())
                                              )
                                          .collect(Collectors.toList());
      
      return new MiDetalleUsuario(
            usuario.getNombre(),
            usuario.getNickname(),
            usuario.getEmail(),
            usuario.getPassword(), auths);
   }
   

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      // TODO Auto-generated method stub
      return authorities;
   }

   @Override
   public String getPassword() {
      // TODO Auto-generated method stub
      return password;
   }

   @Override
   public String getUsername() {
      // TODO Auto-generated method stub
      return usuario;
   }

   @Override
   public boolean isAccountNonExpired() {
      // TODO Auto-generated method stub
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      // TODO Auto-generated method stub
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      // TODO Auto-generated method stub
      return true;
   }

   @Override
   public boolean isEnabled() {
      // TODO Auto-generated method stub
      return true;
   }
   
}
