package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
	private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id) {

        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundExceptions("Objeto Não encontrado id: " + id));

    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {

        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaCpfAndEmail(objDTO);
        //objeto tecnico recebe um tecnico dto para ser salvo
        Tecnico newTec = new Tecnico(objDTO);
        return tecnicoRepository.save(newTec);
    }

 

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationExceptions(
                "Tecnico possui ordens de serviço em aberto"+
                " e não pode ser apagado"
            );
            
        }else{
            tecnicoRepository.deleteById(id);
        }

    }

    //atualizacao de tecnico
    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
       objDTO.setId(id);
       Tecnico oldObj = findById(id);
       validaCpfAndEmail(objDTO);
       oldObj = new Tecnico(objDTO);
       return tecnicoRepository.save(oldObj);
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
