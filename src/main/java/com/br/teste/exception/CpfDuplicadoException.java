package com.br.teste.exception;

public class CpfDuplicadoException extends RuntimeException {
  public CpfDuplicadoException(String message) {
    super(message);
  }
}
