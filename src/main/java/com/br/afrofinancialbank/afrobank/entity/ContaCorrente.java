package com.br.afrofinancialbank.afrobank.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class ContaCorrente extends Conta {
    private Double chequeEspecial;

    public ContaCorrente() {
        this.setTipo("corrente");
        this.chequeEspecial = 1000.0; // exemplo de valor para cheque especial
    }

    public Double getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(Double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    // Outros métodos específicos de ContaCorrente

    public void realizarPagamento(Double valor) {
        if (this.getSaldo() + this.chequeEspecial >= valor) {
            this.setSaldo(this.getSaldo() - valor);
            registrarTransacao("Pagamento", valor);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }

    public void realizarPix(Double valor) {
        if (this.getSaldo() + this.chequeEspecial >= valor) {
            this.setSaldo(this.getSaldo() - valor);
            registrarTransacao("Pix", valor);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }

    public void realizarSaque(Double valor) {
        if (this.getSaldo() + this.chequeEspecial >= valor) {
            // Implementação da lógica de saques mensais gratuitos
            this.setSaldo(this.getSaldo() - valor);
            registrarTransacao("Saque", valor);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }

    private void registrarTransacao(String tipo, Double valor) {
        Transacao transacao = new Transacao();
        transacao.setConta(this);
        transacao.setTipo(tipo);
        transacao.setValor(valor);
        transacao.setData(LocalDateTime.now());
        this.getTransacoes().add(transacao);
    }
}
