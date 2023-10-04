package com.paymybuddy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //configuration de quels filtres seront appliqués à quelles url

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/transferRequest", "/transfer").authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("userEmail")
                        .passwordParameter("password")
                        .permitAll()
                );
        return http.build();
    }
//persistence en memoire du userLogin et du password
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    //     Methode de configuration de SpringSecurity pour vérifier l'existence de l'utilisateur dans le référentiel BDD (authentification) et recuperer son niveau d'authorisation (user, admin).
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


}