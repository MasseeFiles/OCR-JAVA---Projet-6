//package com.paymybuddy.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import javax.sql.DataSource;
//
////essai avec doc spring security - nouvelle version springsecurity 5.4 + tuto baeldung
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        //definition des filtres à appliquer aux differentes requetes http envoyées - configuration
//
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/transferRequest", "/transfer").authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .usernameParameter("userEmail")
//                        .passwordParameter("password")
//                        .permitAll()
//                );
//        return http.build();
//    }
//
//    //     Methode de configuration de SpringSecurity pour vérifier l'existence de l'utilisateur dans le référentiel BDD (authentification) et recuperer son niveau d'authorisation (user, admin).
//    @Autowired
//    void configureGlobal(DataSource pmbDataBase, AuthenticationManagerBuilder auth) throws Exception {
//        //AuthenticationManagerBuilder permet de creer un AuthenticationManager (procedure a suivre pour authentifier et ou trouver les informations a verifier
//        auth.jdbcAuthentication().dataSource(pmbDataBase)
////            .usersByUsernameQuery()
////                .getUserByEmail
////                .getUserRole
////                .encodePassword
//        ;
//    }
//
//
//}