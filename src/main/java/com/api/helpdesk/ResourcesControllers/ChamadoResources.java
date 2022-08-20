package com.api.helpdesk.ResourcesControllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.helpdesk.domain.Chamado;
import com.api.helpdesk.domain.dto.ChamadoDTO;
import com.api.helpdesk.services.ChamadoService;


@RestController()
@RequestMapping("/chamados")
public class ChamadoResources {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));

    }

    @GetMapping()
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> listChamados = chamadoService.findAll();
        List<ChamadoDTO> objDTO = listChamados.stream().map( obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(objDTO);
    }

    
}
