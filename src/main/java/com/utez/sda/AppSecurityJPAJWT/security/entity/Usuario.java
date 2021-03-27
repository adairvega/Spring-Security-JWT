package com.utez.sda.AppSecurityJPAJWT.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Usuario {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idUsuario;
   
   private String nombre;
   
   @Column(unique = true)
   private String nickname;
   
   @Column(unique = true)
   private String email;
   
   private String password;
   
   @ManyToMany(fetch=FetchType.EAGER)
   @JoinTable(
         name = "usuario_rol",
         joinColumns = @JoinColumn(name="id_usuario"),
            inverseJoinColumns = @JoinColumn(name ="rol_id")
         )
   private List<Rol> roles = new ArrayList<Rol>();

   public Usuario() {
   }

   public Usuario(String nombre, String nickname, String email, String password) {
      this.nombre = nombre;
      this.nickname = nickname;
      this.email = email;
      this.password = password;
   }

   public int getIdUsuario() {
      return idUsuario;
   }

   public void setIdUsuario(int idUsuario) {
      this.idUsuario = idUsuario;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getNickname() {
      return nickname;
   }

   public void setNickname(String nickname) {
      this.nickname = nickname;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public List<Rol> getRoles() {
      return roles;
   }

   public void setRoles(List<Rol> roles) {
      this.roles = roles;
   }
   
}
