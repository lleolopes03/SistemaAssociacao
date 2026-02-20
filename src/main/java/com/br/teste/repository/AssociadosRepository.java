package com.br.teste.repository;

import com.br.teste.models.Associados;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociadosRepository extends JpaRepository<Associados,Long> {
    boolean existsByCpf(String cpf);
    Optional<Associados>findByCpf(String cpf);
}
