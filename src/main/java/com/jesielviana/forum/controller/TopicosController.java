package com.jesielviana.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.jesielviana.forum.controller.dto.TopicoDto;
import com.jesielviana.forum.controller.form.AtualizacaoTopicoForm;
import com.jesielviana.forum.controller.form.TopicoForm;
import com.jesielviana.forum.modelo.Topico;
import com.jesielviana.forum.repository.CursoRepository;
import com.jesielviana.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

  @Autowired
  private TopicoRepository topicoRepository;
  @Autowired
  private CursoRepository cursoRepository;

  @GetMapping
  public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso,
      @PageableDefault(sort = "dataCriacao", direction = Direction.DESC) Pageable paginacao) {
    if (nomeCurso == null) {
      Page<Topico> topicos = topicoRepository.findAll(paginacao);
      return TopicoDto.converteDe(topicos);
    } else {
      Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
      return TopicoDto.converteDe(topicos);
    }
  }

  @PostMapping
  @Transactional
  public ResponseEntity<TopicoDto> cadastra(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
    Topico topico = form.converte(cursoRepository);
    topicoRepository.save(topico);
    URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity.created(uri).body(new TopicoDto(topico));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicoDto> detalha(@PathVariable long id) {
    Optional<Topico> optional = topicoRepository.findById(id);
    if (optional.isPresent()) {
      return ResponseEntity.ok(new TopicoDto(optional.get()));
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicoDto> atualiza(@PathVariable long id,
      @RequestBody @Valid AtualizacaoTopicoForm form) {
    Topico topico = form.atualiza(id, topicoRepository);
    return ResponseEntity.ok(new TopicoDto(topico));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> remove(@PathVariable long id) {
    topicoRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}