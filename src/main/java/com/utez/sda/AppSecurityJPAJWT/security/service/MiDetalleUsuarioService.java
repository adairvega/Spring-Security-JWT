package com.utez.sda.AppSecurityJPAJWT.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.utez.sda.AppSecurityJPAJWT.security.entity.MiDetalleUsuario;
import com.utez.sda.AppSecurityJPAJWT.security.entity.Usuario;


@Service
public class MiDetalleUsuarioService implements UserDetailsService{
   
   @Autowired
   UsuarioService usuarioService;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      // TODO Auto-generated method stub
      Usuario usuarioX = usuarioService.getByNickname(username);
      
      if (usuarioX == null) {
         throw new UsernameNotFoundException("Usuario no encontrado");
      }
      return MiDetalleUsuario.construir(usuarioX);
   }

}
