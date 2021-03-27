package com.utez.sda.AppSecurityJPAJWT.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utez.sda.AppSecurityJPAJWT.security.entity.Usuario;
import com.utez.sda.AppSecurityJPAJWT.security.repository.UsuarioRepository;



@Service
public class UsuarioService {

   @Autowired
   UsuarioRepository usuarioRepository;
   
   public Usuario getByNickname(String nickname) {
      return usuarioRepository.findByNickname(nickname);
   }
   
   public boolean existsByNickname(String nickname) {
      return usuarioRepository.existsByNickname(nickname);
   }
   
   public boolean existsByEmail(String email) {
      return usuarioRepository.existsByemail(email);
   }
   
   public void save(Usuario usuario) {
      usuarioRepository.save(usuario);
   }
   
   
}
