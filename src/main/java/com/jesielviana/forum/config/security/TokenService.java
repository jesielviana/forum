package com.jesielviana.forum.config.security;

import java.util.Date;

import com.jesielviana.forum.modelo.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

  @Value("${forum.jwt.expiration}")
  private String expiration;
  @Value("${forum.jwt.secret}")
  private String secret;

  public String geraToken(Authentication authentication) {
    Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
    Date hoje = new Date();
    Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

    return Jwts.builder()
        .setIssuer("Api forum")
        .setSubject(usuarioLogado.getId().toString())
        .setIssuedAt(hoje)
        .setExpiration(dataExpiracao)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public boolean isTokenValido(String token) {
    try {
      Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public long getIdUsuario(String token) {
    Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    return Long.parseLong(claims.getSubject());
  }

}
