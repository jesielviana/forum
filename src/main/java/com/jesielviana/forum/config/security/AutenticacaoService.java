package com.jesielviana.forum.config.security;

import java.util.Optional;

import com.jesielviana.forum.modelo.Usuario;
import com.jesielviana.forum.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
    if (usuario.isPresent()) {
      return usuario.get();
    }
    throw new UsernameNotFoundException("Dados inválidos!");
  }

}
