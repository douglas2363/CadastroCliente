package io.github.douglas2363.cliente.model.repository;

import io.github.douglas2363.cliente.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
