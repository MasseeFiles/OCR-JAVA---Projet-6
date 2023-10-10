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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.authorization.AuthenticatedAuthorizationManager.rememberMe;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //configuration de quels filtres seront appliqués à quelles requetes url
        //procedure de filtrage definie dans configureGlobal() avec jdbcAuthentication
        //ok pour creer ( dofilter() ) et ajouter des filtres customizés
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/transfer", "/transferRequest").authenticated()
                )
                //TODO : enlever les appels aux images logo et favicon du context spring security pour eviter 3 loggers  ("Requete pour l'affichage de la page HTML login");
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("userEmail") //definit dans le form la valeur considérée par spring comme un username
                        .passwordParameter("password")
                        .defaultSuccessUrl("/transfer") // definit la page à afficher si authentification ok
                        .permitAll()
                );
        return http.build();
    }
    //  Methode de configuration de SpringSecurity pour lui indiquer comment trouver les informations necessaire à la comparaison des données fournies par la vue (bdd interne).
    @Autowired
    void configureGlobal(DataSource pmbDataBase, AuthenticationManagerBuilder auth) throws Exception {  //parametre auth permet de configurer le mechanisme d'authentification
        auth.jdbcAuthentication()   //methode particuliere pour authentification via une bdd (JDBC - Java Database Connectivity).
            .dataSource(pmbDataBase)    //bdd à utiliser
            .usersByUsernameQuery("SELECT user_email , password , true FROM users WHERE user_email = ?")
            .authoritiesByUsernameQuery("SELECT user_email , 'user' FROM users WHERE user_email = ?")
        ;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}