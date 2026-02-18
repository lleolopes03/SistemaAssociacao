package com.br.teste.Controller;

import com.br.teste.Service.AssociadoService;
import com.br.teste.dto.AssociadoRequestDto;
import com.br.teste.dto.AssociadoResponseDto;
import com.br.teste.dto.mapper.AssociadoMapper;
import com.br.teste.model.Associado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/associados")
@RequiredArgsConstructor
public class AssociadoController {
    private final AssociadoService service;

    @PostMapping
    public ResponseEntity<AssociadoResponseDto>criar(@RequestBody AssociadoRequestDto dto){
       AssociadoResponseDto responseDto=service.salvar(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }
    @GetMapping("/{id}")
    public ResponseEntity<AssociadoResponseDto>buscarPorId(@PathVariable Long id){
        AssociadoResponseDto responseDto=service.buscarPorId(id);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<AssociadoResponseDto>buscarPorCpf(@PathVariable String cpf){
        AssociadoResponseDto responseDto=service.buscaPorCpf(cpf);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping
    public ResponseEntity<List<AssociadoResponseDto>>buscarTodos(){
        List<AssociadoResponseDto>lista=service.ListasTodos();
        return ResponseEntity.ok(lista);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
