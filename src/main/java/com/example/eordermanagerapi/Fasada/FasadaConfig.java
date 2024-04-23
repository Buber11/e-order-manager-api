package com.example.eordermanagerapi.Fasada;

import com.example.eordermanagerapi.Controller.AuthenticationController;
import com.example.eordermanagerapi.Service.AuthenticationService;
import com.example.eordermanagerapi.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FasadaConfig {

    @Bean
    public Fasada setFasada(AuthenticationService authenticationService){
        return new Fasada(authenticationService);
    }
}
