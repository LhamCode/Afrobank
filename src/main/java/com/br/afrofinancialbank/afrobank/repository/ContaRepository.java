package com.br.afrofinancialbank.afrobank.repository;

import com.br.afrofinancialbank.afrobank.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
