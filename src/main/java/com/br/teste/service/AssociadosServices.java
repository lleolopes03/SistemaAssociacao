package com.br.teste.service;

import com.br.teste.exception.CpfDuplicadoException;
import com.br.teste.exception.CpfJaCadastrado;
import com.br.teste.exception.CpfNaoEncontrado;
import com.br.teste.exception.IdNaoEncontrado;
import com.br.teste.models.Associados;
import com.br.teste.models.dtos.AssociadosRequestDto;
import com.br.teste.models.dtos.AssociadosResponseDto;
import com.br.teste.models.dtos.mapper.AssociadosMapper;
import com.br.teste.repository.AssociadosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssociadosServices {
    private final AssociadosRepository repository;
    private final AssociadosMapper mapper;

    @Transactional
    public AssociadosResponseDto  salvar(AssociadosRequestDto dto){
        if (repository.existsByCpf(dto.getCpf())){
            throw new CpfJaCadastrado("Associado já cadastrado");
        }
        Associados associados=mapper.toEntity(dto);
        return mapper.toResponseDto(repository.save(associados));
    }

    public AssociadosResponseDto buscarPorId(Long id){
        Associados associados=repository.findById(id).orElseThrow(()->new IdNaoEncontrado("Associado não encontrado"));
        return mapper.toResponseDto(associados);

    }
    public AssociadosResponseDto buscarPorCpf(String cpf){
        Associados associados=repository.findByCpf(cpf).orElseThrow(()->new CpfNaoEncontrado("Associado com cpf não encontrado"));
        return mapper.toResponseDto(associados);
    }
    public List<AssociadosResponseDto>buscarTodos(){
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();

    }
    @Transactional
    public void deletar(Long id){
        if (!repository.existsById(id)){
            throw new IdNaoEncontrado("Associado não encontrado");
        }
        repository.deleteById(id);
    }

    @Transactional
    public AssociadosResponseDto editarAssociado(Long id, AssociadosRequestDto dto) {
        // Busca o associado existente
        Associados associados = repository.findById(id)
                .orElseThrow(() -> new IdNaoEncontrado("Id não encontrado"));

        // Verifica se o CPF informado já existe em outro associado
        Optional<Associados> cpfExistente = repository.findByCpf(dto.getCpf());
        if (cpfExistente.isPresent() && !cpfExistente.get().getId().equals(id)) {
            throw new CpfDuplicadoException("CPF já cadastrado para outro associado");
        }

        // Atualiza os campos usando o mapper
        mapper.updateEntityFromDto(dto, associados);

        // Salva novamente no banco
        Associados atualizado = repository.save(associados);

        // Retorna o DTO de resposta
        return mapper.toResponseDto(atualizado);
    }

}
