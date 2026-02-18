package com.br.teste.exception;

public class CpfJaCadastrado extends RuntimeException {
    public CpfJaCadastrado(String message) {
        super(message);
    }
}
