package com.api.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domain.Chamado;
import com.api.helpdesk.domain.Cliente;
import com.api.helpdesk.domain.Tecnico;
import com.api.helpdesk.domain.enums.Perfil;
import com.api.helpdesk.domain.enums.Prioridade;
import com.api.helpdesk.domain.enums.Status;
import com.api.helpdesk.repositorys.ChamadoRepository;
import com.api.helpdesk.repositorys.PessoaRepository;

@Service
public class DBservice {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

    public void instanciaDB(){

		Tecnico tec1 = new Tecnico(null, "Jamerson Souza", "550.482.150-95", "jamerson@mail.com", encoder.encode("123"));
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));

		Cliente cli1 = new Cliente(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", tec1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);

		pessoaRepository.saveAll(Arrays.asList(tec1, tec5, cli1, cli2));
		chamadoRepository.saveAll(Arrays.asList(c1, c2));
    }
    
}
