package br.com.dacatividade3.dacatividade3.services;

import br.com.dacatividade3.dacatividade3.entities.Usuario;
import br.com.dacatividade3.dacatividade3.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(usuario.getEmail())
                        .password(usuario.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }
}