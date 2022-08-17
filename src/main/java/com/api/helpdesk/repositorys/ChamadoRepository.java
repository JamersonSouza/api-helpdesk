package com.api.helpdesk.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{
    
}
