package br.org.generation.blogpessoalnovo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.generation.blogpessoalnovo.model.Usuario;
import br.org.generation.blogpessoalnovo.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
		
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		
		return usuario.map(UserDetailsImpl::new).get();
		
	}
}
