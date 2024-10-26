package de.haraldrichter.car_rental_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Encrypts passwords by hashing them before they get stored in the database.
     * @return Password Encoder Object
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer

                        // Publicly available endpoints that don't need authentication
                        .requestMatchers("/", "/showLoginPage", "/authenticateTheUser", "/showLogoutSuccessPage", "/cars/showAllCars", "/cars/showCarsByQuery", "/cars/showCarById", "/users/accessDenied").permitAll()

                        // Endpoints that need EMPLOYEE role to be accessed:
                        .requestMatchers("/cars/showAddCarForm").hasRole("EMPLOYEE")
                        .requestMatchers("/cars/addCar").hasRole("EMPLOYEE")
                        .requestMatchers("/cars/updateCarForm").hasRole("EMPLOYEE")

                        // Endpoints that need ADMIN role to be accessed:
                        .requestMatchers("/cars/deleteCar").hasRole("ADMIN")

                        // All other endpoints need authentication to be accessed
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateTheUser") // No controller request mapping required for this - it's already built in spring security
                        //.successForwardUrl("/cars/showAllCars")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/showLogoutSuccessPage")
                        .permitAll())
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/accessDenied") // Seite für Zugriffsverweigerung
                );
        return http.build();
    }
}
