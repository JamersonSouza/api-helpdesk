package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.Exceptions.DataIntegrityViolationExceptions;
import com.api.helpdesk.Exceptions.ObjectNotFoundExceptions;
import com.api.helpdesk.domain.Cliente;
import com.api.helpdesk.domain.Pessoa;
import com.api.helpdesk.domain.dto.ClienteDTO;
import com.api.helpdesk.repositorys.ClienteRepository;
import com.api.helpdesk.repositorys.PessoaRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {

        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundExceptions("Objeto Não encontrado id: " + id));

    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {

        objDTO.setId(null);
        validaCpfAndEmail(objDTO);
        //objeto Cliente recebe um Cliente dto para ser salvo
        Cliente newTec = new Cliente(objDTO);
        return clienteRepository.save(newTec);
    }

 

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationExceptions(
                "Cliente possui ordens de serviço em aberto"+
                " e não pode ser apagado"
            );
            
        }else{
            clienteRepository.deleteById(id);
        }

    }

    //atualizacao de Cliente
    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
       objDTO.setId(id);
       Cliente oldObj = findById(id);
       validaCpfAndEmail(objDTO);
       oldObj = new Cliente(objDTO);
       return clienteRepository.save(oldObj);
    }


    private void validaCpfAndEmail( ClienteDTO objDTO) {
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
