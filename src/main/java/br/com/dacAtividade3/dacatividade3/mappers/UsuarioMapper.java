package br.com.dacatividade3.dacatividade3.mappers;

import br.com.dacatividade3.dacatividade3.dtos.RegisterReq;
import br.com.dacatividade3.dacatividade3.entities.Usuario;

public class UsuarioMapper {

    public static Usuario toUsuario(RegisterReq registerReq){
        return new Usuario(registerReq.getEmail(), registerReq.getPassword(), registerReq.getFirstName(), registerReq.getLastName());
    }
}
