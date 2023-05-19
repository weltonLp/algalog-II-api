package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	private EntregaRepository repository;
	
	public Entrega buscar(Long id) {
		return  repository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
}
