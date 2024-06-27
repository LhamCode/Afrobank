package com.br.afrofinancialbank.afrobank.controller;

import com.br.afrofinancialbank.afrobank.dto.DepositoRequest;
import com.br.afrofinancialbank.afrobank.dto.PagamentoRequest;
import com.br.afrofinancialbank.afrobank.dto.SaqueRequest;
import com.br.afrofinancialbank.afrobank.dto.TransferenciaRequest;
import com.br.afrofinancialbank.afrobank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/pagamento/pix")
    public ResponseEntity<String> realizarPixPagamento(@RequestParam Long contaId, @RequestParam Double valor) {
        contaService.realizarPixPagamento(contaId, valor);
        return ResponseEntity.ok("Pix efetuado com Sucesso!");
    }

    @PostMapping("/corrente/pix")
    public ResponseEntity<String> realizarPixCorrente(@RequestParam Long contaId, @RequestParam Double valor) {
        contaService.realizarPixCorrente(contaId, valor);
        return ResponseEntity.ok("Pix efetuado com Sucesso!");
    }

    @PostMapping("/saque")
    public ResponseEntity<String> realizarSaque(@RequestBody SaqueRequest saqueRequest) {
        contaService.realizarSaque(saqueRequest.getContaId(), saqueRequest.getValor());
        return ResponseEntity.ok("Saque realizado com sucesso");
    }

    @PostMapping("/transferencia")
    public ResponseEntity<String>  realizarTransferencia(@RequestBody TransferenciaRequest transferenciaRequest) {
        contaService.realizarTransferencia(transferenciaRequest.getContaOrigemId(), transferenciaRequest.getContaDestinoId(), transferenciaRequest.getValor());
        return ResponseEntity.ok("Transferência efetuada com Sucesso!");
    }

    @PostMapping("/deposito")
    public ResponseEntity<String> realizarDeposito(@RequestBody DepositoRequest depositoRequest) {
        contaService.realizarDeposito(depositoRequest.getContaId(), depositoRequest.getValor());
        return ResponseEntity.ok("Depósito realizado com sucesso");
    }

    @PostMapping("/pagamento")
    public ResponseEntity<String> realizarPagamento(@RequestBody PagamentoRequest pagamentoRequest) {
        contaService.realizarPagamento(pagamentoRequest.getContaId(), pagamentoRequest.getValor());
        return ResponseEntity.ok("Pagamento realizado com sucesso");
    }

}
