package com.diegomarques.testepratico.api.repositories;

import com.diegomarques.testepratico.api.entities.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Page<Conta> findAll(Pageable pageable);
}
