package com.example.eordermanagerapi.payload.request;

public record UserChangesRequest(
        String name,
        String surname,
        String password,
        String email
) {

}