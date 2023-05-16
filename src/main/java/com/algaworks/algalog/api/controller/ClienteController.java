package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	

	//AUTOWIRED DIFICULTA MOCK - por isso podemos usar construtor ou 
	//	@AllArgsConstructor no início da classe
//	public ClienteController(ClienteRepository repository) {
//		super();
//		this.repository = repository;
//	}
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private CatalogoClienteService catalogoClienteService;
			
	@GetMapping
	public List<Cliente> listar() {
		return repository.findAll();	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		
		return repository.findById(id)
				.map(c -> ResponseEntity.ok(c))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente inserir(@Valid @RequestBody Cliente cliente) {	
		return catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente>atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente){
		
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(id);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
	
		catalogoClienteService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
}