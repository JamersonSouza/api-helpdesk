package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.Exceptions.ObjectNotFoundExceptions;
import com.api.helpdesk.domain.Chamado;
import com.api.helpdesk.domain.Cliente;
import com.api.helpdesk.domain.Tecnico;
import com.api.helpdesk.domain.dto.ChamadoDTO;
import com.api.helpdesk.domain.enums.Prioridade;
import com.api.helpdesk.domain.enums.Status;
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

    public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {

        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);
        return chamadoRepository.save(oldObj);

    }

    public Chamado create(@Valid ChamadoDTO objDTO) {
        return chamadoRepository.save(newChamado(objDTO));
    }

    private Chamado newChamado(ChamadoDTO obj){
         //verificação se o id do cliente e do tecnico realmente existe.
        Tecnico tec = tecnicoService.findById(obj.getTecnico());
        Cliente cli = clienteService.findById(obj.getCliente());


        Chamado chamado = new Chamado();
        //verificando se o ID do objeto que vem esta nulo em caso de atualização ou criação
        if(obj.getId() != null){
            chamado.setId(obj.getId());
        }

        chamado.setTecnico(tec);
        chamado.setCliente(cli);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
     }





}
