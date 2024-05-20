package com.example.eordermanagerapi.Fasada;


import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.Author.businsessLogic.AuthorService;
import com.example.eordermanagerapi.Author.businsessLogic.Command.GetAllAuthorsCommand;
import com.example.eordermanagerapi.Author.businsessLogic.Command.GetAuthorCommand;
import com.example.eordermanagerapi.auth.commands.*;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.*;
import com.example.eordermanagerapi.order.businessLogic.Command.AddOrderCommand;
import com.example.eordermanagerapi.order.businessLogic.Command.GetClientOrdersCommand;
import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import com.example.eordermanagerapi.order.businessLogic.OrderService;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.payload.response.ValidateSessionResponse;
import com.example.eordermanagerapi.user.UserServiceImpl;
import com.example.eordermanagerapi.user.command.DeleteUserCommand;
import com.example.eordermanagerapi.user.command.ExistsAsAuthorCommand;
import com.example.eordermanagerapi.user.command.GetUserCommand;
import com.example.eordermanagerapi.user.command.UpdateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

/*
    all services of entities in one place
 */

public class Fasada {

    private UserServiceImpl userService;
    private OrderService orderService;

    private final AuthenticationService authenticationService;
    private EbookService ebookService;
    private AuthorService authorService;
    public Fasada(AuthenticationService theAuthenticationService) {
        authenticationService = theAuthenticationService;
    }
//    authentication

    public ResponseEntity handle(LoginCommand command){
        return command.execute(authenticationService);
    }
    public ResponseEntity handle(SignUpCommand command){
        return command.execute(authenticationService);
    }
    public ResponseEntity handle(ValidateSessionCommand command){
        return command.execute(authenticationService);
    }
    public ResponseEntity handle(RefreshTokenCommand command){
        return command.execute(authenticationService);
    }
    public void handle(LogoutCommand command) {
        command.execute(authenticationService);
    }

//    user

    public ResponseEntity handle(GetUserCommand command){
        return command.execute(userService);
    }
    public ResponseEntity handle(DeleteUserCommand command){
        return command.execute(userService);
    }
    public ResponseEntity handle(UpdateUserCommand command){
        return command.execute(userService);
    }
    public ResponseEntity handle(ExistsAsAuthorCommand command){
        return command.execute(userService);
    }

//    ebook

    public ResponseEntity handle(GetAllEbooksCommand command){
        return command.execute(ebookService);
    }
    public ResponseEntity handle(GetEbookCommand command){
        return command.execute(ebookService);
    }
    public ResponseEntity handle(GetTheMostPopularEbookCommand command){
        return command.execute(ebookService);
    }
    public ResponseEntity handle(GetEbooksAlphabeticalCommand command){
        return command.execute(ebookService);
    }
    public ResponseEntity handle(AddEbookCommnad commnad){
        return commnad.execute(ebookService);
    }

//    author

    public ResponseEntity handle(GetAllAuthorsCommand command){
        return command.execute(authorService);
    }
    public ResponseEntity handle(GetAuthorCommand command){
        return command.execute(authorService);
    }

//    order

    public ResponseEntity handle(AddOrderCommand command){
        return command.execute(orderService);
    }
    public ResponseEntity handle(GetClientOrdersCommand command){
        return command.execute(orderService);
    }


    @Autowired
    @Lazy
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Autowired
    @Lazy
    public void setEbookService(EbookService ebookService) {
        this.ebookService = ebookService;
    }
    @Autowired
    @Lazy
    public void setEbookService(AuthorService authorService) {
        this.authorService = authorService;
    }
    @Autowired
    @Lazy
    public void setEbookService(OrderService orderService) {
        this.orderService = orderService;
    }


}
