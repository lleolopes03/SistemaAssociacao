package com.br.teste.service;

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
        return mapper.toRespondeDto(repository.save(associados));
    }

    public AssociadosResponseDto buscarPorId(Long id){
        Associados associados=repository.findById(id).orElseThrow(()->new IdNaoEncontrado("Associado não encontrado"));
        return mapper.toRespondeDto(associados);

    }
    public AssociadosResponseDto buscarPorCpf(String cpf){
        Associados associados=repository.findByCpf(cpf).orElseThrow(()->new CpfNaoEncontrado("Associado com cpf não encontrado"));
        return mapper.toRespondeDto(associados);
    }
    public List<AssociadosResponseDto>buscarTodos(){
        return repository.findAll().stream()
                .map(mapper::toRespondeDto)
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
    public AssociadosResponseDto editarAssociado(Long id, AssociadosRequestDto dto){
        Associados associados=repository.findById(id).orElseThrow(()->new IdNaoEncontrado("Id não encontrado"));
        mapper.updateEntityFromDto(dto,associados);
        Associados atualizado=repository.save(associados);
        return mapper.toRespondeDto(atualizado);
    }

}
