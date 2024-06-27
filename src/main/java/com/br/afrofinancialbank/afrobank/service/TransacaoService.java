package com.br.afrofinancialbank.afrobank.service;


import com.br.afrofinancialbank.afrobank.exception.SaldoInsuficienteException;
import com.br.afrofinancialbank.afrobank.entity.Conta;
import com.br.afrofinancialbank.afrobank.entity.Transacao;
import com.br.afrofinancialbank.afrobank.repository.ContaRepository;
import com.br.afrofinancialbank.afrobank.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<Transacao> buscarTransacoesPorCliente(Long clienteId) {
        return transacaoRepository.findByContaClienteId(clienteId);
    }

}
