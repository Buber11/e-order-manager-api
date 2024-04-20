package com.example.eordermanagerapi.testController;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.Fasada.commands.GetAllUsersCommand;
import com.example.eordermanagerapi.Entities.User;
import com.example.eordermanagerapi.JPARespository.JPAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    test controller to test new feature
 */
@RestController
public class HelloWorldController {

    Fasada fasada;

    @Autowired
    JPAUserRepository jpaUserRepository;

    public HelloWorldController(Fasada theFasada){
        fasada = theFasada;
    }

    @GetMapping("/helloworld")
    public User helloWorld(){
        return jpaUserRepository.findByEmail("jan.kowalski@ebook.com").get();
    }

}
