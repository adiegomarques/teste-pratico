package com.diegomarques.testepratico.api.services.impl;

import com.diegomarques.testepratico.api.entities.Conta;
import com.diegomarques.testepratico.api.repositories.ContaRepository;
import com.diegomarques.testepratico.api.services.ContaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContaServiceImpl implements ContaService {

    private static final Logger log = LoggerFactory.getLogger(ContaServiceImpl.class);

    @Autowired
    private ContaRepository contaRepository;


    @Cacheable("conta")
    public Page<Conta> listar( Pageable pageable) {
        return this.contaRepository.findAll(pageable);
    }

    @CachePut("conta")
    public Conta persistir(Conta conta) {
        return this.contaRepository.save(conta);
    }

    public void remover(Long id) {
        this.contaRepository.delete(id);
    }

}
