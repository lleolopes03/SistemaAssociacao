package com.br.teste.models.dtos.mapper;

import com.br.teste.models.Associados;
import com.br.teste.models.dtos.AssociadosRequestDto;
import com.br.teste.models.dtos.AssociadosResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AssociadosMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "ativo",constant = "true")
    Associados toEntity(AssociadosRequestDto dto);
    AssociadosResponseDto toRespondeDto(Associados entity);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "ativo",ignore = true)
    void updateEntityFromDto(AssociadosRequestDto dto, @MappingTarget Associados entity);
}
