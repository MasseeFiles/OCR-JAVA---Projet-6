package com.paymybuddy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //configuration de quels filtres seront appliqués à quelles requetes url
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/transfer").authenticated()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/logoPMB.png").permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("userEmail") //definit dans le form la valeur considérée par spring comme un username
                        .passwordParameter("password")
                        .defaultSuccessUrl("/transfer") // definit la page à afficher si authentification ok
                        .permitAll()
                );
        return http.build();
    }

    //  Methode de configuration de SpringSecurity pour lui indiquer comment trouver les informations necessaires à l'authentification (comparaison data données de la vue / bdd interne) et authorisation (recuperation du role du user).
    @Autowired
    void configureGlobal(DataSource projectDataBase, AuthenticationManagerBuilder auth) throws Exception {  //parametre auth permet de configurer le mechanisme d'authentification - configuration d'un filtre particulier
        auth.jdbcAuthentication()   //methode particuliere pour authentification via une bdd (JDBC - Java Database Connectivity).
                .dataSource(projectDataBase)
                .usersByUsernameQuery("SELECT user_email , password , true FROM users WHERE user_email = ?")
                .authoritiesByUsernameQuery("SELECT user_email , 'user' FROM users WHERE user_email = ?")
        ;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}