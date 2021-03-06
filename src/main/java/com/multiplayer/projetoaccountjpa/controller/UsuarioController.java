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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.multiplayer.projetoaccountjpa.model.Usuario;
import com.multiplayer.projetoaccountjpa.service.LoginService;
import com.multiplayer.projetoaccountjpa.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> getTodosUsuarios() {
		return usuarioService.buscarTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable(value="id") Integer id) {
		Optional<Usuario> usuario = usuarioService.buscarPorId(id);

		if (usuario.isPresent()) {
			return ResponseEntity.ok().body(usuario.get());
		} else {

			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario createUser(@Validated @RequestBody Usuario u) throws Exception {
		
		return usuarioService.cadastrarUsuario(u.getNome(), u.getCpf(), u.getLogin(), u.getSenha());
	};

}
