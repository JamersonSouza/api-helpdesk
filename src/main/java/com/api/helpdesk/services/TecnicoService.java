package com.api.helpdesk.services;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.Exceptions.ObjectNotFoundExceptions;
import com.api.helpdesk.domain.Tecnico;
import com.api.helpdesk.repositorys.TecnicoRepository;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {

        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundExceptions("Objeto Não encontrado id: " + id));

    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }
    
    
}
