package com.br.afrofinancialbank.afrobank.config;

import com.br.afrofinancialbank.afrobank.entity.Cliente;
import com.br.afrofinancialbank.afrobank.entity.ContaCorrente;
import com.br.afrofinancialbank.afrobank.entity.ContaPagamento;
import com.br.afrofinancialbank.afrobank.entity.Transacao;
import com.br.afrofinancialbank.afrobank.repository.ClienteRepository;
import com.br.afrofinancialbank.afrobank.repository.ContaRepository;
import com.br.afrofinancialbank.afrobank.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class DatabaseInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public DatabaseInitializer(ClienteRepository clienteRepository, ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("Iniciando inserção de dados no banco...");

        // Criando clientes
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João Silva");
        cliente1.setCpf("12345678901");
        cliente1.setEmail("joao.silva@example.com");
        cliente1.setEndereco("Rua A, 123");
        cliente1.setRendaSalarial(3000.00);
        cliente1.setSenha("senha123");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria Oliveira");
        cliente2.setCpf("98765432100");
        cliente2.setEmail("maria.oliveira@example.com");
        cliente2.setEndereco("Rua B, 456");
        cliente2.setRendaSalarial(1500.00);
        cliente2.setSenha("senha456");

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));

        // Criando contas
        ContaCorrente contaCorrente1 = new ContaCorrente();
        contaCorrente1.setCliente(cliente1);
        contaCorrente1.setSaldo(1000.00);

        ContaPagamento contaPagamento1 = new ContaPagamento();
        contaPagamento1.setCliente(cliente2);
        contaPagamento1.setSaldo(500.00);

        contaRepository.saveAll(Arrays.asList(contaCorrente1, contaPagamento1));

        // Criando transações
        Transacao transacao1 = new Transacao();
        transacao1.setConta(contaCorrente1);
        transacao1.setTipo("Depósito");
        transacao1.setValor(1000.00);
        transacao1.setData(LocalDateTime.now());

        Transacao transacao2 = new Transacao();
        transacao2.setConta(contaPagamento1);
        transacao2.setTipo("Depósito");
        transacao2.setValor(500.00);
        transacao2.setData(LocalDateTime.now());

        transacaoRepository.saveAll(Arrays.asList(transacao1, transacao2));
        log.info("O Banco conectou !!!");
        log.info("Dados inseridos com sucesso no banco de dados!");
    }
}
