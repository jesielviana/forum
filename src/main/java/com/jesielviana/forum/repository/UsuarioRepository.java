package com.jesielviana.forum.repository;

import java.util.Optional;

import com.jesielviana.forum.modelo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByEmail(String email);

}
