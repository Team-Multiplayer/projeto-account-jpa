package com.multiplayer.projetoaccountjpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.multiplayer.projetoaccountjpa.model.Usuario;
import com.multiplayer.projetoaccountjpa.repository.UsuarioRepository;
import com.multiplayer.projetoaccountjpa.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> getAllUsers() {
		return usuarioRepository.findAll();
	}

	@GetMapping("{/id}")
	public ResponseEntity<Usuario> getUser(@PathVariable Integer id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			return ResponseEntity.ok().body(usuario.get());
		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario createUser(@Validated @RequestBody Usuario usuario) throws Exception {
		UsuarioService usuarioService = new UsuarioService();
		
		Boolean checaUsuario = usuarioService.validaUsuario(usuario);
		if (checaUsuario) {
			usuarioRepository.save(usuario);
		}
		
		return usuario;
	};

}
