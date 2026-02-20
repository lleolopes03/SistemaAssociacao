package com.br.teste.controller;

import com.br.teste.models.dtos.AssociadosRequestDto;
import com.br.teste.models.dtos.AssociadosResponseDto;
import com.br.teste.service.AssociadosServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/associados")
@RequiredArgsConstructor
public class AssociadosController {
    private final AssociadosServices services;

    @PostMapping
    public ResponseEntity<AssociadosResponseDto>criar(@RequestBody  @Valid  AssociadosRequestDto dto){
        AssociadosResponseDto responseDto=services.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AssociadosResponseDto>buscarPorId(@PathVariable Long id){
        AssociadosResponseDto responseDto=services.buscarPorId(id);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<AssociadosResponseDto>buscarPorCpf(@PathVariable String cpf){
        AssociadosResponseDto responseDto=services.buscarPorCpf(cpf);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping
    public ResponseEntity<List<AssociadosResponseDto>>buscarTodos(){
        List<AssociadosResponseDto> responseDto=services.buscarTodos();
        return ResponseEntity.ok(responseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AssociadosResponseDto>editarAssociados(@PathVariable Long id,@RequestBody @Valid AssociadosRequestDto dto){
        AssociadosResponseDto responseDto=services.editarAssociado(id,dto);
        return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        services.deletar(id);
        return ResponseEntity.noContent().build();

    }

}
