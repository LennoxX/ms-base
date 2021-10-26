package br.com.lucas.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.lucas.enums.EnumTipoUsuario;
import br.com.lucas.models.Usuario;
import br.com.lucas.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${spring.jwt.secret}")
	private String secret;

	@Value("${spring.jwt.expiration}")
	private Long expiration;

	@Autowired
	private UserService userService;

	public String generateToken(String username) {

		Date now = new Date();
		Date validity = new Date(System.currentTimeMillis() + expiration);

		Claims claims = Jwts.claims().setSubject(username);
		
		List<EnumTipoUsuario> lista = new ArrayList<EnumTipoUsuario>();
		
		Usuario usuario = userService.findByUsername(username);
		
		lista.add(usuario.getNivel());
		
		lista.stream().map((EnumTipoUsuario nivel) -> "ROLE_" + nivel.name());

		claims.put("authorities", lista);

		return Jwts.builder().setSubject(usuario.getUsername()).setClaims(claims).setIssuedAt(now)
				.setExpiration(validity).signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}

	}
}
