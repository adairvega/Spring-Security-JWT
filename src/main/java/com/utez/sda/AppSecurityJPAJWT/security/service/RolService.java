package com.utez.sda.AppSecurityJPAJWT.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utez.sda.AppSecurityJPAJWT.security.entity.Rol;
import com.utez.sda.AppSecurityJPAJWT.security.enums.RolNombre;
import com.utez.sda.AppSecurityJPAJWT.security.repository.RolRepository;



@Service
@Transactional
public class RolService {
   
   @Autowired
   RolRepository rolRepository;
   
   public Rol getByRolNombre(RolNombre rolNombre) {
      return rolRepository.findByRolNombre(rolNombre);
   }
   
   public void save(Rol rol) {
      rolRepository.save(rol);
   }
}
