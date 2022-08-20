package com.api.helpdesk.ResourcesControllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping()
    public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO tecnicoDTO){
        Tecnico newTec = tecnicoService.create(tecnicoDTO);
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(newTec.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    
}
