package com.jesielviana.forum.repository;

import com.jesielviana.forum.modelo.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

  Curso findByNome(String nome);

}