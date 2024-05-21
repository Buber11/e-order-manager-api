package com.example.eordermanagerapi.Fasada;


/**
 * Represents a command that can be executed.
 * @param <T> The type of result returned by the command.
 * @param <U> The type of input required by the command.
 */
public interface Command<T, U> {
    /**
     * Executes the command.
     * @param u The input required by the command.
     * @return The result of executing the command.
     */
    T execute(U u);
}