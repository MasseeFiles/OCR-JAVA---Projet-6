package com.paymybuddy.configuration;

import com.paymybuddy.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//essai avec doc spring security - nouvelle version springsecurity 5.4 + tuto baeldung

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //definition des filtres à appliquer aux differentes requetes http envoyées

        http
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                );
        return http.build();
    }
//     Methode de configuration de SpringSecurity pour vérifier l'existence de l'utilisateur dans le référentiel BDD (authentification) et recuperer son niveau d'authorisation (user, admin).
    @Autowired
    void configureGlobal(DataSource pmbDataBase, AuthenticationManagerBuilder auth) throws Exception {
    //AuthenticationManagerBuilder permet de creer un AuthenticationManager (procedure a suivre pour authentifier et ou trouver les informations a verifier
        auth.jdbcAuthentication().dataSource(pmbDataBase)
                .getUserByEmail
                .getUserRole
                .encodePassword
        ;
    }

      // methode pour recuperer l'utilisateur en BDD - appel de couche repository sans passer par couche service
    @Autowired
    UserRepository userRepository;
    @Bean
    public User getUserByEmail(String userEmail) {
        User userToGet = userRepository.findById(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found : login provided " + userEmail));     //.orElseThrow converti l'optional en User
        return userToGet;
    }

    // methode pour recuperer le role de l'utilisateur
//    @Bean
//    public String getUserRole(User userToCheck) { //question le ROLE est une donnée particuliere ou une string
//        String role = userToCheck.getRole();
//        return role; //admin ou user
//    }

    // methode de codage du mot de passe avec BCryptPasswordEncoder (interface SpringSecurity - passwordEncoder)
    @Bean
    public PasswordEncoder encodePassword() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();   //  PasswordEncoderFactories propose par defaut un cryptage avec BCrypt
        return encoder;
//        @Bean
//        public BCryptPasswordEncoder encodePassword() {
//            return new BCryptPasswordEncoder();
//        }
    }


//  Definition d'un user test avec WebSecurityConfigurerAdapter
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        //PasswordEncoderFactories propose par defaut un cryptage avec BCrypt
//        UserDetails user = User.withUsername("spring")
//                .password(encoder.encode("secret"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }


    //  Definition d'un user test sans WebSecurityConfigurerAdapter

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}