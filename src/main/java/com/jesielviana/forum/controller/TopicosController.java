package com.jesielviana.forum.controller;

import java.util.List;

import com.jesielviana.forum.controller.dto.TopicoDto;
import com.jesielviana.forum.controller.form.TopicoForm;
import com.jesielviana.forum.modelo.Topico;
import com.jesielviana.forum.repository.CursoRepository;
import com.jesielviana.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

  @Autowired
  private TopicoRepository topicoRepository;
  @Autowired
  private CursoRepository cursoRepository;

  @GetMapping
  public List<TopicoDto> lista(String nomeCurso) {
    if (nomeCurso == null) {
      List<Topico> topicos = topicoRepository.findAll();
      return TopicoDto.converteDe(topicos);
    } else {
      List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
      return TopicoDto.converteDe(topicos);
    }
  }

  @PostMapping
  public void cadastra(@RequestBody TopicoForm form) {
    System.out.println(form.getNomeCurso());
    Topico topico = form.converte(cursoRepository);
    topicoRepository.save(topico);
  }

}
