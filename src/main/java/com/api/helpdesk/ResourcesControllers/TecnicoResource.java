package com.api.helpdesk.ResourcesControllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.helpdesk.domain.Tecnico;
import com.api.helpdesk.domain.dto.TecnicoDTO;
import com.api.helpdesk.services.TecnicoService;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico obj = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @GetMapping()
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> listTec = tecnicoService.findAll();
        List<TecnicoDTO> listTecDto = listTec.stream().map(obj -> new TecnicoDTO(obj))
        .collect(Collectors.toList());
        return ResponseEntity.ok().body(listTecDto);        
    }

    //inserir um tecnico
    @PostMapping()
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico newTec = tecnicoService.create(tecnicoDTO);
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(newTec.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    //deletando tecnico
    @DeleteMapping("/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //atualização Tecnico
    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
        Tecnico oldObj = tecnicoService.update(id, objDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(oldObj));
    }
    
}
