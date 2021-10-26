package br.com.lucas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.models.Usuario;
import br.com.lucas.services.UserService;

@RestController
public class UsuarioResource {

	@Autowired
	private UserService service;

	@PostMapping("/sign-up")
	public ResponseEntity<Usuario> register(@RequestBody Usuario usuario) {
		System.out.println(usuario);
		return ResponseEntity.ok().body(this.service.create(usuario));
	}

}
