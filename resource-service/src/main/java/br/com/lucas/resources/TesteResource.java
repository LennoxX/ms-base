package br.com.lucas.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TesteResource {
	
	@GetMapping("/teste")
	public String teste(HttpServletRequest request) {
		return "Teste: " + request.getRequestURL().toString();
	}

}
