package br.com.dacatividade3.dacatividade3.mappers;

import br.com.dacatividade3.dacatividade3.dtos.AvaliacaoDoProfessorDTO;
import br.com.dacatividade3.dacatividade3.dtos.EstagioDTO;
import br.com.dacatividade3.dacatividade3.entities.Aluno;
import br.com.dacatividade3.dacatividade3.entities.Empresa;
import br.com.dacatividade3.dacatividade3.entities.Estagio;
import br.com.dacatividade3.dacatividade3.entities.Orientador;

public class EstagioMapper {

    public static EstagioDTO toEstagioDTO(Estagio estagio) {
        EstagioDTO estagioDTO = new EstagioDTO();
        estagioDTO.setId(estagio.getId());
        estagioDTO.setInicioEstagio(estagio.getInicioEstagio());
        estagioDTO.setFimEstagio(estagio.getFimEstagio());
        estagioDTO.setCargaHoraria(estagio.getCargaHoraria());
        estagioDTO.setStatus(estagio.getStatus());

        if (estagio.getAluno() != null) {
            estagioDTO.setAlunoId(estagio.getAluno().getId());
        }

        if (estagio.getEmpresa() != null) {
            estagioDTO.setEmpresaId(estagio.getEmpresa().getId());
        }

        if (estagio.getOrientador() != null) {
            estagioDTO.setOrientadorId(estagio.getOrientador().getId());
        }

        if (estagio.getAvaliacaoDoProfessor() != null) {
            estagioDTO.setAvaliacaoDoProfessor(AvaliacaoDoProfessorMapper.toAvaliacaoDoProfessorDTO(estagio.getAvaliacaoDoProfessor()));
        }

        if (estagio.getAvaliacaoDaEmpresa() != null) {
            estagioDTO.setAvaliacaoDaEmpresa(AvaliacaoDaEmpresaMapper.toAvaliacaoDaEmpresaDTO(estagio.getAvaliacaoDaEmpresa()));
        }

        return estagioDTO;
    }

    public static Estagio toEstagio(EstagioDTO estagioDTO, Aluno aluno, Empresa empresa, Orientador orientador) {
        Estagio estagio = new Estagio();
        estagio.setId(estagioDTO.getId());
        estagio.setInicioEstagio(estagioDTO.getInicioEstagio());
        estagio.setFimEstagio(estagioDTO.getFimEstagio());
        estagio.setCargaHoraria(estagioDTO.getCargaHoraria());
        estagio.setStatus(estagioDTO.getStatus());

        estagio.setAluno(aluno);
        estagio.setEmpresa(empresa);
        estagio.setOrientador(orientador);

        return estagio;
    }
}
