package br.com.dacatividade3.dacatividade3.dtos;

import java.io.Serializable;

public class AvaliacaoDoProfessorDTO implements Serializable {

    private Long id;
    private String assiduidade;
    private String disciplina;
    private String sociabilidade;
    private String responsabilidade;
    private String iniciativa;
    private Long estagioId;

    public AvaliacaoDoProfessorDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssiduidade() {
        return assiduidade;
    }

    public void setAssiduidade(String assiduidade) {
        this.assiduidade = assiduidade;
    }

    public Long getEstagioId() {
        return estagioId;
    }

    public void setEstagioId(Long estagioId) {
        this.estagioId = estagioId;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getSociabilidade() {
        return sociabilidade;
    }

    public void setSociabilidade(String sociabilidade) {
        this.sociabilidade = sociabilidade;
    }

    public String getResponsabilidade() {
        return responsabilidade;
    }

    public void setResponsabilidade(String responsabilidade) {
        this.responsabilidade = responsabilidade;
    }

    public String getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(String iniciativa) {
        this.iniciativa = iniciativa;
    }
}
