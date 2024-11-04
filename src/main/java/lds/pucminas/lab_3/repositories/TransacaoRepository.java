package lds.pucminas.lab_3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lds.pucminas.lab_3.models.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
    List<Transacao> findByProfessorId(Long professorId);
}
