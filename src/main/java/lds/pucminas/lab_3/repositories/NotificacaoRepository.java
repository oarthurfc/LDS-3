package lds.pucminas.lab_3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lds.pucminas.lab_3.models.Notificacao;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>{
    
}
