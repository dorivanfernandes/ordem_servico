package com.algaworks.algaworks.osworksapi.domain.repository;

import com.algaworks.algaworks.osworksapi.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
