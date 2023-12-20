package br.com.dacatividade3.dacatividade3.mappers;

import br.com.dacatividade3.dacatividade3.dtos.EmpresaDTO;
import br.com.dacatividade3.dacatividade3.entities.Empresa;

public class EmpresaMapper {

    public static EmpresaDTO toEmpresaDTO(Empresa empresa) {
        if (empresa == null) {
            return null;
        }

        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(empresa.getId());
        empresaDTO.setNome(empresa.getNome());
        empresaDTO.setEndereco(empresa.getEndereco());
        empresaDTO.setCnpj(empresa.getCnpj());

        return empresaDTO;
    }

    public static Empresa toEmpresa(EmpresaDTO empresaDTO) {
        if (empresaDTO == null) {
            return null;
        }

        Empresa empresa = new Empresa();
        empresa.setId(empresaDTO.getId());
        empresa.setNome(empresaDTO.getNome());
        empresa.setEndereco(empresaDTO.getEndereco());
        empresa.setCnpj(empresaDTO.getCnpj());

        return empresa;
    }
}
