package com.br.afrofinancialbank.afrobank.service;


import com.br.afrofinancialbank.afrobank.entity.Conta;

import com.br.afrofinancialbank.afrobank.entity.ContaCorrente;
import com.br.afrofinancialbank.afrobank.entity.ContaPagamento;
import com.br.afrofinancialbank.afrobank.entity.Transacao;
import com.br.afrofinancialbank.afrobank.exception.SaldoInsuficienteException;
import com.br.afrofinancialbank.afrobank.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public void realizarPagamento(Long contaId, Double valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        if (conta instanceof ContaCorrente) {
            ContaCorrente contaCorrente = (ContaCorrente) conta;
            if (contaCorrente.getSaldo() + contaCorrente.getChequeEspecial() < valor) {
                throw new SaldoInsuficienteException("Saldo insuficiente");
            }
            contaCorrente.realizarPagamento(valor);
        } else if (conta instanceof ContaPagamento) {
            ContaPagamento contaPagamento = (ContaPagamento) conta;
            if (contaPagamento.getSaldo() < valor) {
                throw new SaldoInsuficienteException("Saldo insuficiente");
            }
            contaPagamento.realizarPagamento(valor);
        }

        registrarTransacao(conta, "Pagamento", valor);
        contaRepository.save(conta);
    }

    @Transactional
    public void realizarPixPagamento(Long contaId, Double valor) {
        ContaPagamento conta = (ContaPagamento) contaRepository.findById(contaId)
                .orElseThrow(() -> new IllegalArgumentException("Conta pagamento não encontrada"));
        if (conta.getSaldo() < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        conta.realizarPix(valor);
        contaRepository.save(conta);
    }



    @Transactional
    public void realizarTransferencia(Long contaOrigemId, Long contaDestinoId, Double valor) {
        Conta contaOrigem = contaRepository.findById(contaOrigemId)
                .orElseThrow(() -> new IllegalArgumentException("Conta de origem não encontrada"));
        Conta contaDestino = contaRepository.findById(contaDestinoId)
                .orElseThrow(() -> new IllegalArgumentException("Conta de destino não encontrada"));

        if (contaOrigem instanceof ContaPagamento) {
            ContaPagamento contaPagamento = (ContaPagamento) contaOrigem;
            if (valor > contaPagamento.getLimiteTransferencia()) {
                throw new IllegalArgumentException("Transferência acima do limite permitido para Conta Pagamento.");
            }
        }

        if (contaOrigem.getSaldo() + getChequeEspecial(contaOrigem) < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);

        registrarTransacao(contaOrigem, "Transferência enviada", valor);
        registrarTransacao(contaDestino, "Transferência recebida", valor);

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);
    }



    @Transactional
    public void realizarPixCorrente(Long contaId, Double valor) {
        ContaCorrente conta = (ContaCorrente) contaRepository.findById(contaId)
                .orElseThrow(() -> new IllegalArgumentException("Conta corrente não encontrada"));
        if (conta.getSaldo() + conta.getChequeEspecial() < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        conta.realizarPix(valor);
        contaRepository.save(conta);
    }

    @Transactional
    public void realizarSaque(Long contaId, Double valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        if (conta.getSaldo() < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo() - valor);
        registrarTransacao(conta, "Saque", valor);
        contaRepository.save(conta);
    }

    @Transactional
    public void realizarDeposito(Long contaId, Double valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        conta.setSaldo(conta.getSaldo() + valor);
        registrarTransacao(conta, "Depósito", valor);
        contaRepository.save(conta);
    }


    private Double getChequeEspecial(Conta conta) {
        if (conta instanceof ContaCorrente) {
            return ((ContaCorrente) conta).getChequeEspecial();
        }
        return 0.0;
    }

    private void registrarTransacao(Conta conta, String tipo, Double valor) {
        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setTipo(tipo);
        transacao.setValor(valor);
        transacao.setData(LocalDateTime.now());
        conta.getTransacoes().add(transacao);
    }
}
