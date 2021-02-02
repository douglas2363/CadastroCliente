package io.github.douglas2363.cliente.service;

import io.github.douglas2363.cliente.model.entity.Usuario;
import io.github.douglas2363.cliente.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                        .findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Login não Encontrado!!"));

        //Cria uma instância do UserDetails ->  Usuario
        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();

    }
}
