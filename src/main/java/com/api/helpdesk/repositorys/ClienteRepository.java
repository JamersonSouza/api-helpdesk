package com.api.helpdesk.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
}
