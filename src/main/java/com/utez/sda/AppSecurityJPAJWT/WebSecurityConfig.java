package com.utez.sda.AppSecurityJPAJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.utez.sda.AppSecurityJPAJWT.security.jwt.JwtEntryPoint;
import com.utez.sda.AppSecurityJPAJWT.security.jwt.JwtTokenFilter;
import com.utez.sda.AppSecurityJPAJWT.security.service.MiDetalleUsuarioService;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   
   @Autowired
   MiDetalleUsuarioService userService;
   
   @Autowired
   JwtEntryPoint jwtEntryCode;
   
   @Bean
   public JwtTokenFilter jwtTokenFilter(){
      return new JwtTokenFilter();
   }
   
   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      // TODO Auto-generated method stub
      
      auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
   }
   
   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
      // TODO Auto-generated method stub
      return super.authenticationManagerBean();
   }
   
   @Bean
   @Override
   protected AuthenticationManager authenticationManager() throws Exception {
      // TODO Auto-generated method stub
      return super.authenticationManager();
   }
   
   
   @Bean
   public DaoAuthenticationProvider AuthenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      // TODO Auto-generated method stub
      
      http.cors().and().csrf().disable()
         .authorizeRequests()
         .antMatchers("/auth/**").permitAll()
         .anyRequest().authenticated()
         .and()
         .exceptionHandling().authenticationEntryPoint(jwtEntryCode)
         .and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      
      http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
      
   }
   
}
