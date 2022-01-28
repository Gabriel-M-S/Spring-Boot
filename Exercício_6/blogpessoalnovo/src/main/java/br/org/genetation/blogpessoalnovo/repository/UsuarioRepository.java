package br.org.genetation.blogpessoalnovo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.genetation.blogpessoalnovo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Optional<Usuario> findByUsuario(String usuario);
}
