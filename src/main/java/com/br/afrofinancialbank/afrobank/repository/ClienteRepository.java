package com.br.afrofinancialbank.afrobank.repository;

import com.br.afrofinancialbank.afrobank.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCpf(String cpf);
    Cliente findByCpfAndSenha(String cpf, String senha);
}