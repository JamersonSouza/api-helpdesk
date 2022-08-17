package com.api.helpdesk.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
    
}
