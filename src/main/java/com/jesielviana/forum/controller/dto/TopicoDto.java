package com.jesielviana.forum.controller.dto;

import java.time.LocalDateTime;

import com.jesielviana.forum.modelo.Topico;

import org.springframework.data.domain.Page;

public class TopicoDto {

  private Long id;
  private String titulo;
  private String mensagem;
  private String nomeCurso;
  private LocalDateTime dataCriacao;

  public TopicoDto(Topico topico) {
    this.id = topico.getId();
    this.titulo = topico.getTitulo();
    this.mensagem = topico.getMensagem();
    this.dataCriacao = topico.getDataCriacao();
    this.nomeCurso = topico.getCurso().getNome();

  }

  public static Page<TopicoDto> converteDe(Page<Topico> topicos) {
    return topicos.map(TopicoDto::new);
  }

  public Long getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public LocalDateTime getDataCriacao() {
    return dataCriacao;
  }

  public String getNomeCurso() {
    return nomeCurso;
  }

}
