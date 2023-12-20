package br.com.dacatividade3.dacatividade3.mappers;

import br.com.dacatividade3.dacatividade3.dtos.AvaliacaoDaEmpresaDTO;
import br.com.dacatividade3.dacatividade3.entities.AvaliacaoDaEmpresa;
import br.com.dacatividade3.dacatividade3.entities.Estagio;

public class AvaliacaoDaEmpresaMapper {

    public static AvaliacaoDaEmpresaDTO toAvaliacaoDaEmpresaDTO(AvaliacaoDaEmpresa avaliacaoDaEmpresa) {
        AvaliacaoDaEmpresaDTO dto = new AvaliacaoDaEmpresaDTO();
        dto.setId(avaliacaoDaEmpresa.getId());
        dto.setRendimentoDeTrabalho(avaliacaoDaEmpresa.getRendimentoDeTrabalho());
        dto.setConhecimentos(avaliacaoDaEmpresa.getConhecimentos());
        dto.setCumprimentoDasTarefas(avaliacaoDaEmpresa.getCumprimentoDasTarefas());
        dto.setAprendizagem(avaliacaoDaEmpresa.getAprendizagem());
        dto.setDesempenho(avaliacaoDaEmpresa.getDesempenho());
        dto.setEstagioId(avaliacaoDaEmpresa.getEstagio().getId());

        return dto;
    }

    public static AvaliacaoDaEmpresa toAvaliacaoDaEmpresa(AvaliacaoDaEmpresaDTO dto, Estagio estagio) {
        AvaliacaoDaEmpresa avaliacaoDaEmpresa = new AvaliacaoDaEmpresa();
        avaliacaoDaEmpresa.setRendimentoDeTrabalho(dto.getRendimentoDeTrabalho());
        avaliacaoDaEmpresa.setConhecimentos(dto.getConhecimentos());
        avaliacaoDaEmpresa.setCumprimentoDasTarefas(dto.getCumprimentoDasTarefas());
        avaliacaoDaEmpresa.setAprendizagem(dto.getAprendizagem());
        avaliacaoDaEmpresa.setDesempenho(dto.getDesempenho());
        avaliacaoDaEmpresa.setEstagio(estagio);

        return avaliacaoDaEmpresa;
    }
}
