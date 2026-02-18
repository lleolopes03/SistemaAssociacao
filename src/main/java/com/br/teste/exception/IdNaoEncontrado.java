package com.br.teste.exception;

public class IdNaoEncontrado extends RuntimeException {
    public IdNaoEncontrado(String message) {
        super(message);
    }
}
