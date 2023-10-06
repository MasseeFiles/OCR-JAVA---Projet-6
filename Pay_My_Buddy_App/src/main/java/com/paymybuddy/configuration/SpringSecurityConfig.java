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
    // 3 interfaces à implementer sous forme de beans : filterChain , userDetailsService , configureglobal
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //configuration de quels filtres seront appliqués à quelles requetes url
        //.authenticated applique les filtres par defaut, du authorisationanager par defaut??
        //ok pouir creer ( dofilter() ) et ajouter des filtres customizés
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/transfer", "/transferRequest").authenticated()
                )
                .formLogin((form) -> form
                        //applique le filtre authorizationFilter
                        .loginPage("/login")
                        .usernameParameter("userEmail") //definit dans le form la valeur considérée par spring comme un username
                        .passwordParameter("password")
                        .defaultSuccessUrl("/transfer") // definit la page à afficher si authentification ok
                        .permitAll()
                );
        return http.build();
    }

    //  synthaxe pour le raccordement à une datasource particulière (datasoource)
    //  public UserDetailsService userDetailsService(DataSource dataSource) {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



//         Methode de configuration de SpringSecurity pour vérifier l'existence de l'utilisateur dans le référentiel BDD (authentification) et recuperer son niveau d'authorisation (user, admin).
    @Autowired
    void configureGlobal(DataSource pmbDataBase, AuthenticationManagerBuilder auth) throws Exception {
        //AuthenticationManagerBuilder permet de creer un AuthenticationManager (procedure a suivre pour authentifier et ou trouver les informations a verifier
        auth.jdbcAuthentication().dataSource(pmbDataBase)
            .usersByUsernameQuery("select user_email , password , true from users where user_email = ?")
            .authoritiesByUsernameQuery( "select user_email , 'user' from users where user_email = ?" )
        ;
    }

}