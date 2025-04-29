package com.example.segundoparcial.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .mvcMatchers("/asignaturas/**").hasAnyRole("RECTOR", "DOCENTE", "ESTUDIANTE")
                        .mvcMatchers("/asignaturas/manage").hasRole("RECTOR")
                        .mvcMatchers("/asignaturas/updateSchedule").hasRole("DOCENTE")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails rector = User.builder()
                .username("rector")
                .password("{noop}password")
                .roles("RECTOR")
                .build();

        UserDetails docente = User.builder()
                .username("docente")
                .password("{noop}password")
                .roles("DOCENTE")
                .build();

        UserDetails estudiante = User.builder()
                .username("estudiante")
                .password("{noop}password")
                .roles("ESTUDIANTE")
                .build();

        return new InMemoryUserDetailsManager(rector, docente, estudiante);
    }
}
