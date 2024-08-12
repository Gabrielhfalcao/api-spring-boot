package com.gabriel.api.repositories;

import com.gabriel.api.usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    UserDetails findByLogin(String login);
}
