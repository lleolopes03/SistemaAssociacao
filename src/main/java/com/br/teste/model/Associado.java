package com.br.teste.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Table(name = "associados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Associado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @CPF
    @Column(unique = true)
    private String cpf;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String telefone;
    @Column(name = "data_adesao",nullable = false)
    private LocalDate dataAdesao;
    @Column(nullable = false)
    private boolean ativo;

}
