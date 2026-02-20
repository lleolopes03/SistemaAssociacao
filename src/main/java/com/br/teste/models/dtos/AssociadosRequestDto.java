package com.br.teste.models.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssociadosRequestDto {
    @NotBlank
    private String nome;
    @CPF
    @NotBlank
    private String cpf;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "\\d{10,11}",message = "telefone invalido")
    private String telefone;
    @PastOrPresent(message = "Data não pode ser no futuro")
    @NotNull(message = "Obrigatório passar a data da adesão")
    private LocalDate dataAdesao;

}
