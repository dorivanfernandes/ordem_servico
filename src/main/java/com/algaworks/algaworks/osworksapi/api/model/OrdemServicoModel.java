package com.algaworks.algaworks.osworksapi.api.model;

import com.algaworks.algaworks.osworksapi.domain.model.StatusOrdemServico;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class OrdemServicoModel {

    private Long id;
    private ClienteResumoModel cliente;
    private String descricao;
    private BigDecimal preco;
    private StatusOrdemServico statusOrdemServico;
    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFinalizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public StatusOrdemServico getStatusOrdemServico() {
        return statusOrdemServico;
    }

    public void setStatusOrdemServico(StatusOrdemServico statusOrdemServico) {
        this.statusOrdemServico = statusOrdemServico;
    }

    public OffsetDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(OffsetDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public OffsetDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public ClienteResumoModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResumoModel cliente) {
        this.cliente = cliente;
    }
}
