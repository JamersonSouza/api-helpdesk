package com.api.helpdesk.ResourcesControlles;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.helpdesk.domain.Cliente;
import com.api.helpdesk.domain.dto.ClienteDTO;
import com.api.helpdesk.services.ClienteService;


@RestController
@RequestMapping(value = "/clientes")
@CrossOrigin(origins = "*")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;


    //lista por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente obj = clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }   
    
    //lista tudo
    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = clienteService.findAll();
        List<ClienteDTO> listDTO = list.stream().map(obj  -> new ClienteDTO(obj))
        .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    //add um Cliente
    @PostMapping()
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){
        Cliente newObj = clienteService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
        path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //atualizando um Cliente
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO){
        Cliente oldObj = clienteService.update(id, objDTO);
        return ResponseEntity.ok().body(new ClienteDTO(oldObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
