package com.example.eordermanagerapi.Fasada;


public interface Command<T,U> {
    T execute(U u);
}
