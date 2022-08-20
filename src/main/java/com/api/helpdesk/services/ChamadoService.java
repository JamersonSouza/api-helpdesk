package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.Exceptions.ObjectNotFoundExceptions;
import com.api.helpdesk.domain.Chamado;
import com.api.helpdesk.repositorys.ChamadoRepository;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundExceptions("Objeto não encontrado" + id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

}
