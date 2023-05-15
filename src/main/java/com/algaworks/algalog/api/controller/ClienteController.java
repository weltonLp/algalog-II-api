package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

@RestController
public class ClienteController {
	

	//AUTOWIRED DIFICULTA MOCK - por isso podemos usar construtor ou 
	//	@AllArgsConstructor no início da classe
//	public ClienteController(ClienteRepository repository) {
//		super();
//		this.repository = repository;
//	}
	
	@Autowired
	private ClienteRepository repository;
	
			
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return repository.findAll();	
	}
	
		
	
	
}
