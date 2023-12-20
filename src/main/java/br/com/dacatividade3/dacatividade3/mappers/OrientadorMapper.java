package br.com.dacatividade3.dacatividade3.mappers;

import br.com.dacatividade3.dacatividade3.dtos.OrientadorDTO;
import br.com.dacatividade3.dacatividade3.entities.Orientador;

public class OrientadorMapper {

    public static OrientadorDTO toOrientadorDTO(Orientador orientador) {
        if (orientador == null) {
            return null;
        }

        OrientadorDTO orientadorDTO = new OrientadorDTO();
        orientadorDTO.setId(orientador.getId());
        orientadorDTO.setNome(orientador.getNome());
        orientadorDTO.setDepartamento(orientador.getDepartamento());

        return orientadorDTO;
    }

    public static Orientador toOrientador(OrientadorDTO orientadorDTO) {
        if (orientadorDTO == null) {
            return null;
        }

        Orientador orientador = new Orientador();
        orientador.setId(orientadorDTO.getId());
        orientador.setNome(orientadorDTO.getNome());
        orientador.setDepartamento(orientadorDTO.getDepartamento());

        return orientador;
    }
}
