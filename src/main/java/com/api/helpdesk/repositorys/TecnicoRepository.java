package com.api.helpdesk.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
    
}
