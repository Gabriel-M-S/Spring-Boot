package br.org.genetation.blogpessoalnovo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.genetation.blogpessoalnovo.model.Tema;

public interface TemaRepository extends JpaRepository<Tema,Long> {
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
}
