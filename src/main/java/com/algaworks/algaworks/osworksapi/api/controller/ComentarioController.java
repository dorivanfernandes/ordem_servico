package com.algaworks.algaworks.osworksapi.api.controller;

import com.algaworks.algaworks.osworksapi.api.model.ComentarioInput;
import com.algaworks.algaworks.osworksapi.api.model.ComentarioModel;
import com.algaworks.algaworks.osworksapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algaworks.osworksapi.domain.model.Comentario;
import com.algaworks.algaworks.osworksapi.domain.model.OrdemServico;
import com.algaworks.algaworks.osworksapi.domain.repository.OrdemServicoRepository;
import com.algaworks.algaworks.osworksapi.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordem-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionarComentario(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput){
        Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());

        return toModel(comentario);
    }

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId){
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId).orElseThrow(()-> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));

        return toCollectionModel(ordemServico.getComentarios());
    }

    private ComentarioModel toModel(Comentario comentario){
        return modelMapper.map(comentario, ComentarioModel.class);

    }

    private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios){
        return comentarios.stream().map(comentario -> toModel(comentario)).collect(Collectors.toList());
    }
}
