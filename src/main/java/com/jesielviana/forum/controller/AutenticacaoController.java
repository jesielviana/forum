package com.jesielviana.forum.controller;

import javax.validation.Valid;

import com.jesielviana.forum.config.security.TokenService;
import com.jesielviana.forum.controller.dto.TokenDto;
import com.jesielviana.forum.controller.form.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AutenticacaoController {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<TokenDto> autentica(@RequestBody @Valid LoginForm form) {
    UsernamePasswordAuthenticationToken login = form.converte();
    try {
      Authentication authentication = authenticationManager.authenticate(login);
      String token = tokenService.geraToken(authentication);
      return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    } catch (AuthenticationException exception) {
      return ResponseEntity.badRequest().build();
    }
  }

}
