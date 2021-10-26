package br.com.lucas.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.lucas.models.security.UsuarioDetails;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;

	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String headerAuthorization = request.getHeader("Authorization");
		if (headerAuthorization != null && headerAuthorization.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken token = getAuthentication(response, headerAuthorization.split("Bearer ")[1]);
			if (token != null) {
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletResponse response, String token) {
		if (jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UsuarioDetails user = (UsuarioDetails) userDetailsService.loadUserByUsername(username);
			response.addHeader("Authorization", "Bearer " + jwtUtil.generateToken(username));
			response.addHeader("Access-Control-Expose-Headers", "Authorization");
			return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
		}
		return null;
	}

}
