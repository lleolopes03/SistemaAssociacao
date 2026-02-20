package com.br.teste.exception;

public class CpfNaoEncontrado extends RuntimeException {
    public CpfNaoEncontrado(String message) {
        super(message);
    }
}
