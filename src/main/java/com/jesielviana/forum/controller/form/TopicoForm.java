package com.jesielviana.forum.controller.form;

import com.jesielviana.forum.modelo.Curso;
import com.jesielviana.forum.modelo.Topico;
import com.jesielviana.forum.repository.CursoRepository;

public class TopicoForm {

  private String titulo;
  private String mensagem;
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
