package de.haraldrichter.car_rental_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Encrypts passwords by hashing them, so that there are no plain text passwords stored in the database.
     * @return Password Encoder Object
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder)
//                .and()
//                .build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity  http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                //.requestMatchers("/").permitAll()
                                .requestMatchers("/cars/showAllCars").permitAll()
                                .requestMatchers("/cars/showCarsByQuery").permitAll()
                                .requestMatchers("/cars/showCarById").permitAll()
                                .requestMatchers("/cars/showAddCarForm").hasRole("EMPLOYEE")
                                .requestMatchers("/cars/addCar").hasRole("EMPLOYEE")
                                .requestMatchers("/cars/updateCarForm").hasRole("EMPLOYEE")
                                .requestMatchers("/cars/deleteCar").hasRole("ADMIN")
                                .anyRequest().authenticated() // Alle requestes müssen authentifiziert werden
//                )
//                .formLogin(form -> // Hier definieren wir unser Login-Formular
//                        form
//                                .loginPage("/showMyLoginPage")
//                                .loginProcessingUrl("/authenticateTheUser")
//                                .permitAll() // jeder kann auf die Login-Seite, ohne eingelogged sein zu müssen - wäre ja sonst bissl schwierig...
//                )
//                .logout(logout -> logout.permitAll()
//                )
//                .exceptionHandling(configurer ->
//                        configurer.accessDeniedPage("/accessDenied")
                );
        return http.build();
    }
}
