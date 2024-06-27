package com.br.afrofinancialbank.afrobank.repository;

import com.br.afrofinancialbank.afrobank.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaClienteId(Long clienteId);
}