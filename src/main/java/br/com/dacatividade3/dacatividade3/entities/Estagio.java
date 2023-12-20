package br.com.dacatividade3.dacatividade3.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Estagio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date inicioEstagio;

    @Temporal(TemporalType.DATE)
    private Date fimEstagio;

    private int cargaHoraria;

    private String status;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Orientador orientador;

    @OneToOne(mappedBy = "estagio", cascade = CascadeType.ALL, orphanRemoval =
            true)
    private AvaliacaoDoProfessor avaliacaoDoProfessor;

    @OneToOne(mappedBy = "estagio", cascade = CascadeType.ALL, orphanRemoval =
            true)
    private AvaliacaoDaEmpresa avaliacaoDaEmpresa;

    public Estagio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInicioEstagio() {
        return inicioEstagio;
    }

    public void setInicioEstagio(Date inicioEstagio) {
        this.inicioEstagio = inicioEstagio;
    }

    public Date getFimEstagio() {
        return fimEstagio;
    }

    public void setFimEstagio(Date fimEstagio) {
        this.fimEstagio = fimEstagio;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    public AvaliacaoDoProfessor getAvaliacaoDoProfessor() {
        return avaliacaoDoProfessor;
    }

    public void setAvaliacaoDoProfessor(AvaliacaoDoProfessor
                                                avaliacaoDoProfessor) {
        this.avaliacaoDoProfessor = avaliacaoDoProfessor;
    }

    public AvaliacaoDaEmpresa getAvaliacaoDaEmpresa() {
        return avaliacaoDaEmpresa;
    }

    public void setAvaliacaoDaEmpresa(AvaliacaoDaEmpresa avaliacaoDaEmpresa) {
        this.avaliacaoDaEmpresa = avaliacaoDaEmpresa;
    }

    public boolean professorAvaliou() {
        return avaliacaoDoProfessor != null;
    }

    public boolean empresaAvaliou() {
        return avaliacaoDaEmpresa != null;
    }
}