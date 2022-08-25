package com.api.helpdesk.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.api.helpdesk.domain.Tecnico;
import com.api.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TecnicoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer id;
    @NotEmpty(message = "Campo NOME não pode ser vazio.")
    protected String nome;
    @NotEmpty(message = "Campo CPF não pode ser vazio.")
    @CPF(message = "Informe um CPF válido")
    protected String cpf;
    @NotEmpty(message = "Campo E-mail não pode ser vazio.")
    @Email(message = "Informe um E-mail Válido.")
    protected String email;
    @Size(min = 8, message = "Campo SENHA deve conter no mínimo 8 caracteres.")
    protected String senha;

    protected Set<Integer> perfil = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO(){
        addPerfil(Perfil.CLIENTE);
    }

    public TecnicoDTO(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfil = obj.getPerfil().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfil() {
        return perfil.stream().map( x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfil.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    
    
}
