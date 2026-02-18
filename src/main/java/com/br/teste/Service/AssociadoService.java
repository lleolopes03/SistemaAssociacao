package com.br.teste.Service;

import com.br.teste.dto.AssociadoRequestDto;
import com.br.teste.dto.AssociadoResponseDto;
import com.br.teste.dto.mapper.AssociadoMapper;
import com.br.teste.exception.CpfJaCadastrado;
import com.br.teste.exception.IdNaoEncontrado;
import com.br.teste.model.Associado;
import com.br.teste.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    private final AssociadoRepository repository;

    public AssociadoResponseDto salvar(AssociadoRequestDto dto){
        if (repository.existsByCpf(dto.getCpf())){
            throw new CpfJaCadastrado("Cpf já cadastrado");
        }
        Associado associado= AssociadoMapper.toEntity(dto);
        Associado associadoSalvo=repository.save(associado);
        return AssociadoMapper.toResponseDto(associadoSalvo);
    }
    public AssociadoResponseDto buscaPorCpf(String cpf){
        Associado associado=repository.findByCpf(cpf).orElseThrow(()->new RuntimeException("Cpf não encontrado"));
        return AssociadoMapper.toResponseDto(associado);

    }
    public AssociadoResponseDto buscarPorId(Long id){
        Associado associado=repository.findById(id).orElseThrow(()->new RuntimeException("Associado não encontrado"));
        return AssociadoMapper.toResponseDto(associado);
    }
    public List<AssociadoResponseDto>ListasTodos(){
        List<Associado>associadoList=repository.findAll();
        return AssociadoMapper.toResponseDto(associadoList);
    }
    public void deletar(Long id){
        if (!repository.existsById(id)){
            throw  new IdNaoEncontrado("Associado não encontrado");
        }
        repository.deleteById(id);
    }
}
