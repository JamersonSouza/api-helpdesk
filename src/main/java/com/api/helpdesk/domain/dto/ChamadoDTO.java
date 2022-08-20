package com.api.helpdesk.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.api.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ChamadoDTO implements Serializable{

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyy")
    private LocalDate dataFechamento;
    @NotEmpty(message =  "O Campo PRIORIDADE Não pode ficar vazio.")
    private Integer prioridade;
    @NotEmpty(message =  "O Campo STATUS Não pode ficar vazio.")
    private Integer status;
    @NotEmpty(message =  "O Campo TITULO Não pode ficar vazio.")
    private String titulo;
    @NotEmpty(message =  "O Campo OBSERVAÇÕES Não pode ficar vazio.")
    private String observacoes;

    @NotEmpty(message =  "O Campo TÉCNICO Não pode ficar vazio.")
    private Integer tecnico;
    @NotEmpty(message =  "O Campo CLIENTE Não pode ficar vazio.")
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.nomeTecnico = obj.getCliente().getNome();
        this.nomeCliente = obj.getTecnico().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    
    
}
