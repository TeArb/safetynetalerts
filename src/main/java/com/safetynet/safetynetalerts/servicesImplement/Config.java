package com.safetynet.safetynetalerts.servicesImplement;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.safetynet.safetynetalerts.repositories.IFirestationsRepository")
public class Config {
/*
    @Bean
    public IFirestationsRepository iFirestationsRepository(IFirestationsRepository iFirestationsRepository) {
        return this.iFirestationsRepository = iFirestationsRepository;
    }*/
}