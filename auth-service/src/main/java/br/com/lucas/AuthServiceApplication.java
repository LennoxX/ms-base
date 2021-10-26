package br.com.lucas;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.lucas.enums.EnumTipoUsuario;
import br.com.lucas.models.Usuario;
import br.com.lucas.repositories.UsuarioRepository;

@SpringBootApplication
@EnableEurekaClient
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			iniciarUsuario(usuarioRepository, passwordEncoder);
		};
	}

	public void iniciarUsuario(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
		List<Usuario> usuarios = repository.findAll();

		if (usuarios.isEmpty()) {

			Usuario usuario = new Usuario();
			usuario.setUsername("admin"); 
			usuario.setPassword(passwordEncoder.encode("@dm1n1str@dor"));
			usuario.setAtivo(true);
			usuario.setNivel(EnumTipoUsuario.ADMIN);

			repository.save(usuario);
		}
	}
}
