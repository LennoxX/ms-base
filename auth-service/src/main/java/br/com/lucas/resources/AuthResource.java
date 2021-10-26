package br.com.lucas.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@GetMapping(value = "/validate")
	public ResponseEntity<?> validate(HttpServletResponse response) {
		return ResponseEntity.noContent().build();
	}

}
