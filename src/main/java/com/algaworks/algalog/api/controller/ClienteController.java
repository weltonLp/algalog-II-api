package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		
		
		
		Cliente c1 = new Cliente(1L, "Júlio", "julio@email.com","6297777-4462");
		Cliente c2 = new Cliente(2L, "Mary", "mary@email.com","6296666-4462");
		Cliente c3 = new Cliente(3L, "Rute", "rute@email.com","6295555-4462");
		
		
		return Arrays.asList(c1, c2, c3);
	}
	
	
}
