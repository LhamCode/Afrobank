package com.br.afrofinancialbank.afrobank.service;

import com.br.afrofinancialbank.afrobank.dto.SaldoResponse;
import com.br.afrofinancialbank.afrobank.entity.Conta;
import com.br.afrofinancialbank.afrobank.entity.ContaCorrente;
import com.br.afrofinancialbank.afrobank.entity.ContaPagamento;
import com.br.afrofinancialbank.afrobank.exception.ClienteJaCadastradoException;
import com.br.afrofinancialbank.afrobank.entity.Cliente;
import com.br.afrofinancialbank.afrobank.repository.ClienteRepository;
import com.br.afrofinancialbank.afrobank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        if (!isValidCPF(cliente.getCpf()) || !isValidEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("CPF ou email inválidos.");
        }

        if (clienteRepository.findByCpf(cliente.getCpf()) != null) {
            throw new ClienteJaCadastradoException("Cliente já cadastrado");
        }

        Cliente novoCliente;
        try {
            novoCliente = clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new ClienteJaCadastradoException("Cliente já cadastrado");
        }

        Conta conta;

        if (cliente.getRendaSalarial() < 2500.00) {
            ContaPagamento contaPagamento = new ContaPagamento();
            contaPagamento.setCliente(novoCliente);
            contaPagamento.setSaldo(0.0);
            contaPagamento.setTipo("Pagamento");
            conta = contaRepository.save(contaPagamento);
        } else {
            ContaCorrente contaCorrente = new ContaCorrente();
            contaCorrente.setCliente(novoCliente);
            contaCorrente.setSaldo(0.0);
            contaCorrente.setTipo("Corrente");
            conta = contaRepository.save(contaCorrente);
        }
        List<Conta> contas = new ArrayList<>();
        contas.add(conta);
        novoCliente.setContas(contas);

        return clienteRepository.save(novoCliente);
    }

    public Cliente loginCliente(String cpf, String senha) {
        Cliente cliente = clienteRepository.findByCpfAndSenha(cpf, senha);
        if (cliente == null) {
            throw new IllegalArgumentException("CPF ou senha inválidos.");
        }
        return cliente;
    }

    private boolean isValidCPF(String cpf) {

        return cpf.matches("\\d{11}");
    }

    private boolean isValidEmail(String email) {

        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public Cliente atualizarCliente(Long id, Cliente novosDados) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        //adicionar condição de Cliente não encontrado
        cliente.setNome(novosDados.getNome());
        cliente.setEmail(novosDados.getEmail());
        cliente.setEndereco(novosDados.getEndereco());
        cliente.setRendaSalarial(novosDados.getRendaSalarial());

        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public SaldoResponse consultarSaldo(Long contaId) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        Cliente cliente = conta.getCliente();

        SaldoResponse saldoResponse = new SaldoResponse();
        saldoResponse.setNomeCliente(cliente.getNome());
        saldoResponse.setSaldo(conta.getSaldo());
        saldoResponse.setTipoConta(conta.getTipo());

        if (conta instanceof ContaCorrente) {
            ContaCorrente contaCorrente = (ContaCorrente) conta;
            Double chequeEspecialUtilizado = contaCorrente.getChequeEspecial();
            if (contaCorrente.getSaldo() < 0) {
                chequeEspecialUtilizado += contaCorrente.getSaldo(); // Saldo é negativo
            }
            saldoResponse.setLimiteChequeEspecial(chequeEspecialUtilizado);
        } else {
            saldoResponse.setLimiteChequeEspecial(0.0);
        }

        return saldoResponse;
    }

}
