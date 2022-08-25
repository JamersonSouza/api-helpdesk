package com.api.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domain.Pessoa;
import com.api.helpdesk.repositorys.PessoaRepository;
import com.api.helpdesk.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Optional<Pessoa> user = pessoaRepository.findByEmail(email);
        if(user.isPresent()){
            return new UserDetailsImpl(user.get().getId(), user.get().getEmail(),
             user.get().getSenha(), user.get().getPerfil());
        }

        throw new UsernameNotFoundException(email);

    }
    
}
