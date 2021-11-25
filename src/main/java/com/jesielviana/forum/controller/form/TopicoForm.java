package com.jesielviana.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.jesielviana.forum.modelo.Curso;
import com.jesielviana.forum.modelo.Topico;
import com.jesielviana.forum.repository.CursoRepository;

import org.hibernate.validator.constraints.Length;

public class TopicoForm {

  @NotNull
  @NotEmpty
  @Length(min = 5, max = 50)
  private String titulo;
  @NotNull
  @NotEmpty
  @Length(min = 10, max = 255)
  private String mensagem;
  @NotNull
  @NotEmpty
  private String nomeCurso;

  public Topico converte(CursoRepository cursoRepository) {
    Curso curso = cursoRepository.findByNome(this.nomeCurso);
    return new Topico(this.titulo, this.mensagem, curso);
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getNomeCurso() {
    return nomeCurso;
  }

  public void setNomeCurso(String nomeCurso) {
    this.nomeCurso = nomeCurso;
  }

}
