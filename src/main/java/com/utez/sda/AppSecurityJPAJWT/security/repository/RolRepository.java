package com.utez.sda.AppSecurityJPAJWT.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utez.sda.AppSecurityJPAJWT.security.entity.Rol;
import com.utez.sda.AppSecurityJPAJWT.security.enums.RolNombre;

@Repository
public interface RolRepository  extends JpaRepository<Rol, Integer>{
   
   com.utez.sda.AppSecurityJPAJWT.security.entity.Rol findByRolNombre(RolNombre rolNombre);
   
}
