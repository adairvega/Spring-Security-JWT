package com.utez.sda.AppSecurityJPAJWT.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utez.sda.AppSecurityJPAJWT.security.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
   
   Usuario findByNickname(String nickname);
   boolean existsByNickname(String nickname);
   boolean existsByemail(String email);
}
