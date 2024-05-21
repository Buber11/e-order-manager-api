package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import jakarta.servlet.http.Cookie;

/**
 * Command for logging out the user.
 */
public class LogoutCommand implements Command<Void, AuthenticationService> {

    private final Cookie cookie;

    /**
     * Constructs a LogoutCommand with the specified JWT token cookie.
     *
     * @param cookie the JWT token cookie to invalidate
     */
    private LogoutCommand(Cookie cookie) {
        this.cookie = cookie;
    }

    /**
     * Creates a new LogoutCommand instance with the provided JWT token cookie.
     *
     * @param cookie the JWT token cookie to invalidate
     * @return a new LogoutCommand instance
     */
    public static LogoutCommand from(Cookie cookie) {
        return new LogoutCommand(cookie);
    }

    /**
     * Executes the logout command by delegating the logout process to the AuthenticationService.
     *
     * @param authenticationService the AuthenticationService to execute the command on
     * @return null
     */
    @Override
    public Void execute(AuthenticationService authenticationService) {
        authenticationService.logout(cookie);
        return null;
    }
}
