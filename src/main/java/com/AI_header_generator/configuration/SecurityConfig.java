package com.AI_header_generator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider, userDetailsService);
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .authorizeHttpRequests(matcherRegistry -> matcherRegistry

                        //endpointy dla usera
                        .requestMatchers("/zadanie/user/wyswietl-liste").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/zadanie/user/wyswietl/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/zadanie/user/update").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/zadanie/user/{taskId}/subtasks").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/zadanie/user/twoje-zadania").hasAnyRole("USER", "ADMIN")

                        .requestMatchers("zadanie/podzadanie/user/wyswietl-liste/{parentTaskId}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("zadanie/podzadanie/user/{parentTaskId}/aktualizuj/{subtaskId}").hasAnyRole("USER", "ADMIN")

                        //endpointy dla admina
                        .requestMatchers("/zadanie/admin/dodaj").hasRole("ADMIN")
                        .requestMatchers("/zadanie/admin/usun/{id}").hasRole("ADMIN")
                        .requestMatchers("/zadanie/admin/{taskId}/addsubtask").hasRole("ADMIN")
                        .requestMatchers("/zadanie/admin/dodaj").hasRole("ADMIN")

                        .requestMatchers("/zadanie/podzadanie/admin/{taskId}/dodaj").hasRole("ADMIN")
                        .requestMatchers("/zadanie/podzadanie/admin/{parentTaskId}/usun/{subtaskId}").hasRole("ADMIN")

                        .requestMatchers("/uzytkownik/admin/wyswietl-liste").hasRole("ADMIN")
                        .requestMatchers("/uzytkownik/admin/dodaj").hasRole("ADMIN")
                        .requestMatchers("/uzytkownik/admin/usun/{userId}").hasRole("ADMIN")

                        //endppint do logowania
                        .requestMatchers("/login").permitAll()
                        //.requestMatchers("/uzytkownik/register").permitAll()

                        .requestMatchers(HttpMethod.GET, "/*").permitAll()

                        .anyRequest().denyAll()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }
}
