package com.br.afrofinancialbank.afrobank.controller;


import com.br.afrofinancialbank.afrobank.dto.SaldoResponse;
import com.br.afrofinancialbank.afrobank.entity.Cliente;
import com.br.afrofinancialbank.afrobank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }
    @PostMapping("/login")
    public Cliente loginCliente(@RequestBody Cliente cliente) {
        return clienteService.loginCliente(cliente.getCpf(), cliente.getSenha());
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente novosDados) {
        return clienteService.atualizarCliente(id, novosDados);
    }

    @GetMapping("/{cpf}")
    public Cliente buscarPorCpf(@PathVariable String cpf) {
        return clienteService.buscarPorCpf(cpf);
    }

    @GetMapping("/{id}/saldo")
    public SaldoResponse consultarSaldo(@PathVariable Long id) {
        return clienteService.consultarSaldo(id);
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

}