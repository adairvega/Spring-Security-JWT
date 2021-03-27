package com.utez.sda.AppSecurityJPAJWT.security.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.utez.sda.AppSecurityJPAJWT.security.dto.JwtDto;
import com.utez.sda.AppSecurityJPAJWT.security.dto.LoginUsuario;
import com.utez.sda.AppSecurityJPAJWT.security.jwt.JwtProvider;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class LoginController {
   
   @Autowired
   AuthenticationManager authenticationManager;
   
   @Autowired
   JwtProvider jwtProvider;
   
   @PostMapping("/login")
   public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
      if(bindingResult.hasErrors()) {
         return new ResponseEntity("Datos incompletos", HttpStatus.BAD_REQUEST);
      }
      try {
         Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                     loginUsuario.getNickname(),
                     loginUsuario.getPassword())
               );
         SecurityContextHolder.getContext().setAuthentication(authentication);
         String token = jwtProvider.generarToken(authentication);
         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         JwtDto dto = new JwtDto(token, userDetails.getUsername(), userDetails.getAuthorities());
         return new ResponseEntity(dto, HttpStatus.OK);
      } catch (BadCredentialsException e) {
         return new ResponseEntity("Usuario y/o contraseña incorrectos.", HttpStatus.BAD_REQUEST);
      }
   }
}
