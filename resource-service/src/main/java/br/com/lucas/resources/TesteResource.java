package br.com.lucas.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TesteResource {
	@Value("${server.port}")
	private int port;
	
	@GetMapping("/teste")
	public String teste() {
		System.out.println(port);
		return "Teste";
	}

}
