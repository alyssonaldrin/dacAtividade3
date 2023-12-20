package br.com.dacatividade3.dacatividade3.mappers;

import br.com.dacatividade3.dacatividade3.dtos.AvaliacaoDoProfessorDTO;
import br.com.dacatividade3.dacatividade3.entities.AvaliacaoDoProfessor;
import br.com.dacatividade3.dacatividade3.entities.Estagio;

public class AvaliacaoDoProfessorMapper {

    public static AvaliacaoDoProfessorDTO toAvaliacaoDoProfessorDTO(AvaliacaoDoProfessor avaliacaoDoProfessor) {
        AvaliacaoDoProfessorDTO dto = new AvaliacaoDoProfessorDTO();
        dto.setId(avaliacaoDoProfessor.getId());
        dto.setAssiduidade(avaliacaoDoProfessor.getAssiduidade());
        dto.setDisciplina(avaliacaoDoProfessor.getDisciplina());
        dto.setSociabilidade(avaliacaoDoProfessor.getSociabilidade());
        dto.setResponsabilidade(avaliacaoDoProfessor.getResponsabilidade());
        dto.setIniciativa(avaliacaoDoProfessor.getIniciativa());
        dto.setEstagioId(avaliacaoDoProfessor.getEstagio().getId());

        return dto;
    }

    public static AvaliacaoDoProfessor toAvaliacaoDoProfessor(AvaliacaoDoProfessorDTO dto, Estagio estagio) {
        AvaliacaoDoProfessor avaliacaoDoProfessor = new AvaliacaoDoProfessor();
        avaliacaoDoProfessor.setAssiduidade(dto.getAssiduidade());
        avaliacaoDoProfessor.setDisciplina(dto.getDisciplina());
        avaliacaoDoProfessor.setSociabilidade(dto.getSociabilidade());
        avaliacaoDoProfessor.setResponsabilidade(dto.getResponsabilidade());
        avaliacaoDoProfessor.setIniciativa(dto.getIniciativa());
        avaliacaoDoProfessor.setEstagio(estagio);

        return avaliacaoDoProfessor;
    }
}
