package com.br.afrofinancialbank.afrobank.controller;


import com.br.afrofinancialbank.afrobank.entity.Transacao;
import com.br.afrofinancialbank.afrobank.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/cliente/{clienteId}")
    public List<Transacao> buscarTransacoesPorCliente(@PathVariable Long clienteId) {
        return transacaoService.buscarTransacoesPorCliente(clienteId);
    }
}
