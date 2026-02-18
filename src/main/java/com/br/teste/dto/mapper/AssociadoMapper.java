package com.br.teste.dto.mapper;

import com.br.teste.dto.AssociadoRequestDto;
import com.br.teste.dto.AssociadoResponseDto;
import com.br.teste.model.Associado;

import java.util.List;

public class AssociadoMapper {

    public static Associado toEntity(AssociadoRequestDto dto){
        if (dto ==null){
            return null;
        }
        return Associado.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .dataAdesao(dto.getDataAdesao())
                .ativo(true)
                .build();

    }
    public static AssociadoResponseDto toResponseDto(Associado entity){
        if (entity ==null){
            return null;
        }
        return AssociadoResponseDto.builder()
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .email(entity.getEmail())
                .telefone(entity.getTelefone())
                .dataAdesao(entity.getDataAdesao())
                .ativo(entity.isAtivo())
                .build();

    }
    public static List<AssociadoResponseDto>toResponseDto(List<Associado>entities){
        if (entities==null)return List.of();
        return entities.stream()
                .map(AssociadoMapper::toResponseDto)
                .toList();



    }
}
