package com.utez.sda.AppSecurityJPAJWT.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
   
   @PreAuthorize("hasRole('USER')")
   @GetMapping("/servicioUser")
   public ResponseEntity<?> servicioUser(){
      return new ResponseEntity("Hola servicio user", HttpStatus.OK);
   }
   
   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping("/servicioAdmin")
   public ResponseEntity<?> servicioAdmin(){
      return new ResponseEntity("Hola servicio user", HttpStatus.OK);
   }
}
