package com.api.helpdesk.services;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.Exceptions.DataIntegrityViolationExceptions;
import com.api.helpdesk.Exceptions.ObjectNotFoundExceptions;
import com.api.helpdesk.domain.Pessoa;
import com.api.helpdesk.domain.Tecnico;
import com.api.helpdesk.domain.dto.TecnicoDTO;
import com.api.helpdesk.repositorys.PessoaRepository;
import com.api.helpdesk.repositorys.TecnicoRepository;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {

        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundExceptions("Objeto Não encontrado id: " + id));

    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {

        objDTO.setId(null);
        validaCpfAndEmail(objDTO);
        //objeto tecnico recebe um tecnico dto para ser salvo
        Tecnico newTec = new Tecnico(objDTO);
        return tecnicoRepository.save(newTec);
    }

    private void validaCpfAndEmail( TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
          throw new DataIntegrityViolationExceptions("CPF Já Cadastrado no Sistema");
        }
 
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
         throw new DataIntegrityViolationExceptions("E-mail já cadastrado no sistema.");
        }
 
     }


    
}
