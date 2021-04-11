package com.algaworks.algaworks.osworksapi.domain.model;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrdemServico ordemServico;

    private String descricao;

    private OffsetDateTime data_envio;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return Objects.equals(id, that.id) && Objects.equals(ordemServico, that.ordemServico) && Objects.equals(descricao, that.descricao) && Objects.equals(data_envio, that.data_envio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ordemServico, descricao, data_envio);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OffsetDateTime getData_envio() {
        return data_envio;
    }

    public void setData_envio(OffsetDateTime data_envio) {
        this.data_envio = data_envio;
    }
}
