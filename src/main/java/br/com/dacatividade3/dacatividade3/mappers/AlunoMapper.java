package br.com.dacatividade3.dacatividade3.mappers;

import br.com.dacatividade3.dacatividade3.dtos.AlunoDTO;
import br.com.dacatividade3.dacatividade3.entities.Aluno;

public class AlunoMapper {

    public static AlunoDTO toAlunoDTO(Aluno aluno) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setMatricula(aluno.getMatricula());
        alunoDTO.setCurso(aluno.getCurso());

        return alunoDTO;
    }

    public static Aluno toAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setMatricula(alunoDTO.getMatricula());
        aluno.setCurso(alunoDTO.getCurso());

        return aluno;
    }
}
