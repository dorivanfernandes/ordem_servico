package com.algaworks.algaworks.osworksapi.domain.service;

import com.algaworks.algaworks.osworksapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algaworks.osworksapi.domain.exception.NegocioException;
import com.algaworks.algaworks.osworksapi.domain.model.Cliente;
import com.algaworks.algaworks.osworksapi.domain.model.Comentario;
import com.algaworks.algaworks.osworksapi.domain.model.OrdemServico;
import com.algaworks.algaworks.osworksapi.domain.model.StatusOrdemServico;
import com.algaworks.algaworks.osworksapi.domain.repository.ClienteRepository;
import com.algaworks.algaworks.osworksapi.domain.repository.ComentarioRepository;
import com.algaworks.algaworks.osworksapi.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public OrdemServico criar(OrdemServico ordemServico){
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId()).orElseThrow(() -> new NegocioException("Cliente não encontrado"));
        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public void finalizarOrdemServico(Long ordemServicoId){
        OrdemServico ordemServico = buscar(ordemServicoId);


        ordemServico.finalizar();

        ordemServicoRepository.save(ordemServico);
    }

    private OrdemServico buscar(Long ordemServicoId) {
        return ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
    }

    public Comentario adicionarComentario(Long ordemServicoId, String descricao){
        OrdemServico ordemServico = buscar(ordemServicoId);
        

        Comentario comentario = new Comentario();
        comentario.setData_envio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);

    }
}
