package br.com.dacatividade3.dacatividade3.services;

import br.com.dacatividade3.dacatividade3.dtos.RegisterReq;
import br.com.dacatividade3.dacatividade3.entities.Usuario;
import br.com.dacatividade3.dacatividade3.mappers.UsuarioMapper;
import br.com.dacatividade3.dacatividade3.repositories.UsuarioRepository;
import br.com.dacatividade3.dacatividade3.services.exceptions.PasswordMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Transactional
    public void insert(RegisterReq registerReq) throws PasswordMismatchException {
        if(!Objects.equals(registerReq.getPassword(), registerReq.getConfirmPassword())){
            throw new PasswordMismatchException();
        }

        Usuario entity = UsuarioMapper.toUsuario(registerReq);
        usuarioRepository.save(entity);
    }
}
