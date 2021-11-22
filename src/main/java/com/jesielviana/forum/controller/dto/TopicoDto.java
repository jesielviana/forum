package com.jesielviana.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.jesielviana.forum.modelo.Topico;

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

  public static List<TopicoDto> converteDe(List<Topico> topicos) {
    return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
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
