package com.jesielviana.forum.repository;

import java.util.List;

import com.jesielviana.forum.modelo.Topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

  List<Topico> findByTitulo(String titulo);

  List<Topico> findByCursoNome(String nomeCurso);

}
