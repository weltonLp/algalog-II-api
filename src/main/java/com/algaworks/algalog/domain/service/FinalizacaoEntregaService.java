package com.algaworks.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;


@Service
public class FinalizacaoEntregaService {
	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	
	@Transactional
	public void finalizar (Long id) {
		Entrega entrega = buscaEntregaService.buscar(id);
		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
	}
	
}
