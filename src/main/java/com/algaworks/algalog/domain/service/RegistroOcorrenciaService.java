package com.algaworks.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;

//@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
	
	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long id, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(id);
		
		return entrega.adicionarOcorrencia(descricao);
		
	}
}
