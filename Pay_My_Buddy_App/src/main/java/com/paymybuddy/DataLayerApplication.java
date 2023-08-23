package com.paymybuddy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //annotation pour indiquer emplacement de Main classe
public class DataLayerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataLayerApplication.class, args);
    }

    @Override         // Verification de l'acces à la BDD - recuperation des users
    public void run(String... args) throws Exception {
    }

}

