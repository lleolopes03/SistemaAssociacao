package com.br.teste.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssociadoRequestDto {
    @NotBlank
    private String nome;
    @CPF
    private String cpf;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "\\d{10,11}",message = "telefone invalido")
    private String telefone;
    @NotNull(message = "data adesão é obrigatória")
    @PastOrPresent(message = "Data de adesão não pode ser futura")
    private LocalDate dataAdesao;

}
