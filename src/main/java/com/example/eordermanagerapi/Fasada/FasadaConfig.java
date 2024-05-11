package com.example.eordermanagerapi.Fasada;

import com.example.eordermanagerapi.auth.AuthenticationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FasadaConfig {

    @Bean
    public Fasada setFasada(AuthenticationServiceImpl authenticationService){
        return new Fasada(authenticationService);
    }

}
