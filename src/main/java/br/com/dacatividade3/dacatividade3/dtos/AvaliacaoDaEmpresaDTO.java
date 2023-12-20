package br.com.dacatividade3.dacatividade3.dtos;

import java.io.Serializable;

public class AvaliacaoDaEmpresaDTO implements Serializable {

    private Long id;
    private String rendimentoDeTrabalho;
    private String conhecimentos;
    private String cumprimentoDasTarefas;
    private String aprendizagem;
    private String desempenho;
    private Long estagioId;

    public AvaliacaoDaEmpresaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRendimentoDeTrabalho() {
        return rendimentoDeTrabalho;
    }

    public void setRendimentoDeTrabalho(String rendimentoDeTrabalho) {
        this.rendimentoDeTrabalho = rendimentoDeTrabalho;
    }

    public Long getEstagioId() {
        return estagioId;
    }

    public void setEstagioId(Long estagioId) {
        this.estagioId = estagioId;
    }

    public String getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(String conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

    public String getCumprimentoDasTarefas() {
        return cumprimentoDasTarefas;
    }

    public void setCumprimentoDasTarefas(String cumprimentoDasTarefas) {
        this.cumprimentoDasTarefas = cumprimentoDasTarefas;
    }

    public String getAprendizagem() {
        return aprendizagem;
    }

    public void setAprendizagem(String aprendizagem) {
        this.aprendizagem = aprendizagem;
    }

    public String getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(String desempenho) {
        this.desempenho = desempenho;
    }
}
