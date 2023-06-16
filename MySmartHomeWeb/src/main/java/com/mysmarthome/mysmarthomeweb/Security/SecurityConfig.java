package com.mysmarthome.mysmarthomeweb.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig{

   /*@Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().loginPage("/login").defaultSuccessUrl("http:/localhost:4200", true);
        http.authorizeRequests().requestMatchers("/inscription","/login").permitAll(); //mettre inscription dans ADMIN après creéation premier user
        http.authorizeRequests().requestMatchers("http:/localhost:4200").hasAuthority("USER");
        http.authorizeRequests().requestMatchers("http:/localhost:4200/administration").hasAuthority("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");
        http.logout();
        http.httpBasic();
        return http.build();
    }*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().loginPage("/login").defaultSuccessUrl("http:/localhost:4200", true);
        http.authorizeRequests().requestMatchers("/inscription","/login").permitAll(); //mettre inscription dans ADMIN après creéation premier user
        http.authorizeRequests().requestMatchers("http:/localhost:4200").hasAuthority("USER");
        http.authorizeRequests().requestMatchers("http:/localhost:4200/administration").hasAuthority("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");
        http.logout();
        http.httpBasic();
           http.httpBasic(withDefaults());
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}