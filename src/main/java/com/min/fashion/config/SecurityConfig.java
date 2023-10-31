package com.min.fashion.config;

import com.min.fashion.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("user")
        .password("password")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    // For simplicity, using NoOpPasswordEncoder. In a real application, use a secure password encoder.
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .requestMatchers("/").permitAll()
        .requestMatchers("/login").permitAll()
        .requestMatchers("/user").hasRole("USER")
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().accessDeniedPage("/accessDenied")
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/").permitAll()
        .and()
        .oauth2Login()
        .loginPage("/login")
        .userInfoEndpoint();
  }
}









/*
@EnableWebFluxSecurity
public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
  @Autowired
  CustomOAuth2UserService customOAuth2UserService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/user").hasRole("USER")
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().accessDeniedPage("/accessDenied")
        .and()
        .logout().logout("/logout")
        .logoutSuccessUrl("/").permitAll()
        .and()
        .oauth2Login().loginPage("/login")
        .userInfoEndpoint()
        .userService(customOAuth2UserService);
  }
}

requestMatchers
 */