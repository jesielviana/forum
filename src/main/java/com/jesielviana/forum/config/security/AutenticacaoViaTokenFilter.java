package com.jesielviana.forum.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jesielviana.forum.modelo.Usuario;
import com.jesielviana.forum.repository.UsuarioRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

  private TokenService tokenService;
  private UsuarioRepository usuarioRepository;

  public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
    String token = pegarTokenDoHeader(request);
    boolean tokenValido = tokenService.isTokenValido(token);
    if (tokenValido) {
      autenticaCliente(token);
    }

    filterChain.doFilter(request, response);

  }

  private void autenticaCliente(String token) {
    long idUsuario = tokenService.getIdUsuario(token);
    Usuario usuario = usuarioRepository.findById(idUsuario).get();
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null,
        usuario.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

  }

  private String pegarTokenDoHeader(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
      return null;
    }
    return token.substring(7, token.length());
  }

}
