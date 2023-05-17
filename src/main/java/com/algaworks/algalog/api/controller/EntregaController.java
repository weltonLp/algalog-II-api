package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.model.DestinatarioModel;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega entrega(@Valid @RequestBody Entrega entrega) {
		return solicitacaoEntregaService.solicitar(entrega);
	}

	@GetMapping
	public List<Entrega> listar(){
		return entregaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntregaModel> buscar( @PathVariable Long id){
		return entregaRepository.findById(id)
				.map(x ->{
					EntregaModel entregaModel = new EntregaModel();
					entregaModel.setId(x.getId());
					entregaModel.setNomeCliente(x.getCliente().getNome());
					
					
					DestinatarioModel destinatario = new DestinatarioModel();
					destinatario.setNome(x.getDestinatario().getNome());
					destinatario.setLogradouro(x.getDestinatario().getLogradouro());
					destinatario.setNumero(x.getDestinatario().getNumero());
					destinatario.setComplemento(x.getDestinatario().getComplemento());
					destinatario.setBairro(x.getDestinatario().getBairro());
					
					entregaModel.setDestinatario(destinatario);
					entregaModel.setTaxa(x.getTaxa());
					entregaModel.setStatus(x.getStatus());
					entregaModel.setDataPedido(x.getDataPedido());
					entregaModel.setDataFinalizacao(x.getDataFinalizacao());
					
					
//					entregaModel.setDestinatario(new DestinatarioModel());
//					entregaModel.getDestinatario().setNome(x.getDestinatario().getNome());
//					entregaModel.getDestinatario().setLogradouro(x.getDestinatario().getLogradouro());
//					entregaModel.getDestinatario().setNumero(x.getDestinatario().getNumero());
//					entregaModel.getDestinatario().setComplemento(x.getDestinatario().getComplemento());
//					entregaModel.getDestinatario().setBairro(x.getDestinatario().getBairro());
					
					return ResponseEntity.ok(entregaModel);
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
}