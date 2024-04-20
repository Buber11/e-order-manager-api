package com.example.eordermanagerapi.Fasada.commands;


public interface Command<T,U> {
    T execute(U u);
}
