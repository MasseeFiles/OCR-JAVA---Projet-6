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

import static org.springframework.security.authorization.AuthenticatedAuthorizationManager.rememberMe;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    // 3 interfaces à implementer sous fomre de beans : filterChain , userDetailsService , configureglobal
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
//                        .successForwardUrl("/transfer") // definit la page à afficher si authentification ok
                        .permitAll()
                );
        return http.build();
    }

    //  synthaxe pour le raccordement à une datasource particulière (datasoource)
    //  public UserDetailsService userDetailsService(DataSource dataSource) {
    @Bean
    public UserDetailsService userDetailsService() {
        //permet de persister EN MEMOIRE des infos concernant un utilisateur (ici, son username et password) PRIS DANS L'APPLI (ici la bdd) grace a l'objet UserDetails (user)-
        // d'ou mot de passe encodé

        //UserDetailsService est une interface definissant methode loadUserByUsername(String username)
        //prend en parametre username et renvoi un objet userdetails avec parametres issus de la BDD pour authentification (nom, pass, role, etc.) defini dans userBuilder???

        UserDetails user =
                User.builder()
                        //autres données possibles ex - role, numero de compte...
                        .username("user")
                        .password(passwordEncoder().encode("password"))
                        .build();
        return new InMemoryUserDetailsManager(user);
        // ou se fait la comparaison avec le login et password donnés par la vue ? via l'objet authentication? automatiquement??

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //AuthenticationManagerBuilder permet de creer un AuthenticationManager (mechanisme à utiliser par Spring Security pour authentifier un user - regles de comparaison ici???
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password");
    }

//  bean permettant le branchement à une BDD particulière
//    @Bean
//    public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
//
//        return new JdbcUserDetailsManager(dataSource);
//    }

}