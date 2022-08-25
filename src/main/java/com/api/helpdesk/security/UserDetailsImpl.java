package com.api.helpdesk.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.helpdesk.domain.enums.Perfil;

public class UserDetailsImpl implements UserDetails{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String senha;

    private Collection<? extends GrantedAuthority> authorities;

    //construtor 
    

    public Integer getId(){
        return id;
    }

    public UserDetailsImpl(Integer id, String email, String senha, Set<Perfil> perfils) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = perfils.stream()
        .map( x -> new SimpleGrantedAuthority(x.getDescricao())).
        collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return senha;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
