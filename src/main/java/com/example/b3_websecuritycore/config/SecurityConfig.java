package com.example.b3_websecuritycore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")// nhấn submit gửi lên action /login
                        .defaultSuccessUrl("/user/")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling() // truy cập không đúng vai trò
                .accessDeniedPage("/access-demied");
        http.oauth2Login().loginPage("/auth/login/form")
                .defaultSuccessUrl("/oauth/login/form", true)
                .failureUrl("/auth/loin/error")
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization");
        return http.build();
    }
}
