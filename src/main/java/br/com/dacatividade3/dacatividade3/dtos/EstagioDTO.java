package br.com.dacatividade3.dacatividade3.dtos;

import java.io.Serializable;
import java.util.Date;

public class EstagioDTO implements Serializable {

    private Long id;
    private Date inicioEstagio;
    private Date fimEstagio;
    private int cargaHoraria;
    private String status;
    private Long alunoId;
    private Long empresaId;
    private Long orientadorId;
    private AvaliacaoDoProfessorDTO avaliacaoDoProfessor;
    private AvaliacaoDaEmpresaDTO avaliacaoDaEmpresa;

    public EstagioDTO() {
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

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
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

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getOrientadorId() { return orientadorId; }

    public void setOrientadorId(Long orientadorId) {
        this.orientadorId = orientadorId;
    }

    public AvaliacaoDoProfessorDTO getAvaliacaoDoProfessor() {
        return avaliacaoDoProfessor;
    }

    public void setAvaliacaoDoProfessor(AvaliacaoDoProfessorDTO avaliacaoDoProfessor) {
        this.avaliacaoDoProfessor = avaliacaoDoProfessor;
    }

    public AvaliacaoDaEmpresaDTO getAvaliacaoDaEmpresa() {
        return avaliacaoDaEmpresa;
    }

    public void setAvaliacaoDaEmpresa(AvaliacaoDaEmpresaDTO avaliacaoDaEmpresa) {
        this.avaliacaoDaEmpresa = avaliacaoDaEmpresa;
    }
}
