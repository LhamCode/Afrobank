package com.br.afrofinancialbank.afrobank;

import com.br.afrofinancialbank.afrobank.controller.ContaController;
import com.br.afrofinancialbank.afrobank.dto.DepositoRequest;
import com.br.afrofinancialbank.afrobank.dto.PagamentoRequest;
import com.br.afrofinancialbank.afrobank.dto.SaqueRequest;
import com.br.afrofinancialbank.afrobank.dto.TransferenciaRequest;
import com.br.afrofinancialbank.afrobank.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ContaControllerTests {

    @InjectMocks
    private ContaController contaController;

    @Mock
    private ContaService contaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRealizarPixPagamento() {
        doNothing().when(contaService).realizarPixPagamento(anyLong(), anyDouble());

        ResponseEntity<String> response = contaController.realizarPixPagamento(1L, 100.0);

        assertEquals("Pix efetuado com Sucesso!", response.getBody());
        verify(contaService, times(1)).realizarPixPagamento(anyLong(), anyDouble());
    }

    @Test
    void testRealizarPixCorrente() {
        doNothing().when(contaService).realizarPixCorrente(anyLong(), anyDouble());

        ResponseEntity<String> response = contaController.realizarPixCorrente(1L, 100.0);

        assertEquals("Pix efetuado com Sucesso!", response.getBody());
        verify(contaService, times(1)).realizarPixCorrente(anyLong(), anyDouble());
    }

    @Test
    void testRealizarSaque() {
        SaqueRequest saqueRequest = new SaqueRequest();
        saqueRequest.setContaId(1L);
        saqueRequest.setValor(100.0);
        doNothing().when(contaService).realizarSaque(anyLong(), anyDouble());

        ResponseEntity<String> response = contaController.realizarSaque(saqueRequest);

        assertEquals("Saque realizado com sucesso", response.getBody());
        verify(contaService, times(1)).realizarSaque(anyLong(), anyDouble());
    }

    @Test
    void testRealizarTransferencia() {
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setContaOrigemId(1L);
        transferenciaRequest.setContaDestinoId(2L);
        transferenciaRequest.setValor(100.0);
        doNothing().when(contaService).realizarTransferencia(anyLong(), anyLong(), anyDouble());

        ResponseEntity<String> response = contaController.realizarTransferencia(transferenciaRequest);

        assertEquals("Transferência efetuada com Sucesso!", response.getBody());
        verify(contaService, times(1)).realizarTransferencia(anyLong(), anyLong(), anyDouble());
    }

    @Test
    void testRealizarDeposito() {
        DepositoRequest depositoRequest = new DepositoRequest();
        depositoRequest.setContaId(1L);
        depositoRequest.setValor(100.0);
        doNothing().when(contaService).realizarDeposito(anyLong(), anyDouble());

        ResponseEntity<String> response = contaController.realizarDeposito(depositoRequest);

        assertEquals("Depósito realizado com sucesso", response.getBody());
        verify(contaService, times(1)).realizarDeposito(anyLong(), anyDouble());
    }

    @Test
    void testRealizarPagamento() {
        PagamentoRequest pagamentoRequest = new PagamentoRequest();
        pagamentoRequest.setContaId(1L);
        pagamentoRequest.setValor(100.0);
        doNothing().when(contaService).realizarPagamento(anyLong(), anyDouble());

        ResponseEntity<String> response = contaController.realizarPagamento(pagamentoRequest);

        assertEquals("Pagamento realizado com sucesso", response.getBody());
        verify(contaService, times(1)).realizarPagamento(anyLong(), anyDouble());
    }
}
