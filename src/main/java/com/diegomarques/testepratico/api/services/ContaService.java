package com.diegomarques.testepratico.api.services;

import com.diegomarques.testepratico.api.entities.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContaService {



    /**
     * Retorna lista de contas.
     *
     * @return Page<Conta>
     */
    Page<Conta> listar( Pageable pageable);
	

	
	/**
	 * Persiste uma conta na base de dados.
	 * 
	 * @return Conta
	 */
	Conta persistir(Conta conta);
	
	/**
	 * Remove um lançamento da base de dados.
	 * 
	 * @param id
	 */
	void remover(Long id);
	
}
