package br.com.lucas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.lucas.models.Usuario;
import br.com.lucas.models.security.UsuarioDetails;
import br.com.lucas.repositories.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		try {
			Optional<Usuario> opt = repository.findByUsername(username);

			Usuario usuario = opt.get();
			opt.orElseThrow();
			UsuarioDetails userDetails = new UsuarioDetails(usuario.getUsername(), usuario.getPassword(),
					usuario.getAtivo(), false, false, true, usuario.getNivel().name());
			return userDetails;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

}
