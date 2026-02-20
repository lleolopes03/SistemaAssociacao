package com.br.teste.serviceTeste;


import com.br.teste.exception.CpfJaCadastrado;
import com.br.teste.exception.IdNaoEncontrado;
import com.br.teste.models.Associados;
import com.br.teste.models.dtos.AssociadosRequestDto;
import com.br.teste.models.dtos.AssociadosResponseDto;
import com.br.teste.models.dtos.mapper.AssociadosMapper;
import com.br.teste.repository.AssociadosRepository;
import com.br.teste.service.AssociadosServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociadosServicesTest {

    @Mock
    private AssociadosRepository repository;

    @Mock
    private AssociadosMapper mapper;

    @InjectMocks
    private AssociadosServices services;

    private AssociadosRequestDto requestDto;
    private Associados associado;
    private AssociadosResponseDto responseDto;

    @BeforeEach
    void setUp() {
        requestDto = AssociadosRequestDto.builder()
                .nome("João Silva")
                .cpf("529.982.247-25")
                .email("joao@email.com")
                .telefone("31987654321")
                .dataAdesao(LocalDate.now())
                .build();

        associado = Associados.builder()
                .id(1L)
                .nome("João Silva")
                .cpf("529.982.247-25")
                .email("joao@email.com")
                .telefone("31987654321")
                .dataAdesao(LocalDate.now())
                .ativo(true)
                .build();

        responseDto = AssociadosResponseDto.builder()
                .id(1L)
                .nome("João Silva")
                .cpf("529.982.247-25")
                .email("joao@email.com")
                .telefone("31987654321")
                .dataAdesao(LocalDate.now())
                .ativo(true)
                .build();
    }

    @Test
    void deveSalvarAssociadoComSucesso() {
        when(repository.existsByCpf(requestDto.getCpf())).thenReturn(false);
        when(mapper.toEntity(requestDto)).thenReturn(associado);
        when(repository.save(associado)).thenReturn(associado);
        when(mapper.toRespondeDto(associado)).thenReturn(responseDto);

        AssociadosResponseDto resultado = services.salvar(requestDto);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        assertEquals(1L, resultado.getId());
        verify(repository, times(1)).save(associado);
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaCadastrado() {
        when(repository.existsByCpf(requestDto.getCpf())).thenReturn(true);

        assertThrows(CpfJaCadastrado.class, () -> services.salvar(requestDto));
        verify(repository, never()).save(any(Associados.class));
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoEncontrado() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IdNaoEncontrado.class, () -> services.buscarPorId(99L));
    }
}
