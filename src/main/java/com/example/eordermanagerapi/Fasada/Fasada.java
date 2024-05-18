package com.example.eordermanagerapi.Fasada;


import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.Author.businsessLogic.AuthorService;
import com.example.eordermanagerapi.Author.businsessLogic.Command.GetAllAuthorsCommand;
import com.example.eordermanagerapi.Author.businsessLogic.Command.GetAuthorCommand;
import com.example.eordermanagerapi.auth.commands.*;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.GetAllEbooksCommand;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.GetEbookCommand;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.GetEbooksAlphabeticalCommand;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.GetTheMostPopularEbookCommand;
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
import org.springframework.context.annotation.Lazy;

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

   public JwtResponse handle(LoginCommand command){
        return command.execute(authenticationService);
   }
   public Optional<UserInfoResponse> handle(SignUpCommand command){
        return command.execute(authenticationService);
   }
   public UserInfoResponse handle(GetUserCommand command){
        return command.execute(userService);
   }
   public Boolean handle(DeleteUserCommand command){
        return command.execute(userService);
   }
   public JwtResponse handle(UpdateUserCommand command){
        return command.execute(userService);
   }
   public boolean handle(ExistsAsAuthorCommand command){
        return command.execute(userService);
   }
   public ValidateSessionResponse handle(ValidateSessionCommand command){
        return command.execute(authenticationService);
   }
   public JwtResponse handle(RefreshTokenCommand command){
        return command.execute(authenticationService);
   }

   public List<EbookDTOView> handle(GetAllEbooksCommand command){
        return command.execute(ebookService);
   }
   public EbookDTOView handle(GetEbookCommand command){
        return command.execute(ebookService);
   }
   public void handle(LogoutCommand command) {
        command.execute(authenticationService);
    }
   public List<EbookDTOView> handle(GetTheMostPopularEbookCommand command){
        return command.execute(ebookService);
   }
   public List<EbookDTOView> handle(GetEbooksAlphabeticalCommand command){
        return command.execute(ebookService);
   }
   public List<AuthorDTOView> handle(GetAllAuthorsCommand command){
        return command.execute(authorService);
   }
    public List<OrderDtoView> handle(GetClientOrdersCommand command){
        return command.execute(orderService);
    }
   public AuthorDTOView handle(GetAuthorCommand command){
        return command.execute(authorService);
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
