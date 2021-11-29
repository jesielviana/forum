package com.jesielviana.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.jesielviana.forum.modelo.Curso;
import com.jesielviana.forum.modelo.Topico;
import com.jesielviana.forum.repository.CursoRepository;
import com.jesielviana.forum.repository.TopicoRepository;

import org.hibernate.validator.constraints.Length;

public class AtualizacaoTopicoForm {

  @NotNull
  @NotEmpty
  @Length(min = 5, max = 50)
  private String titulo;
  @NotNull
  @NotEmpty
  @Length(min = 10, max = 255)
  private String mensagem;

  public Topico atualiza(long id, TopicoRepository topicoRepository) {
    Topico topico = topicoRepository.getById(id);
    topico.setTitulo(this.titulo);
    topico.setMensagem(this.mensagem);
    return topico;
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

}
