package com.br.afrofinancialbank.afrobank;

import com.br.afrofinancialbank.afrobank.controller.TransacaoController;
import com.br.afrofinancialbank.afrobank.entity.Transacao;
import com.br.afrofinancialbank.afrobank.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TransacaoControllerTests {

    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private TransacaoService transacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarTransacoesPorCliente() {
        List<Transacao> transacoes = List.of(new Transacao());
        when(transacaoService.buscarTransacoesPorCliente(anyLong())).thenReturn(transacoes);

        List<Transacao> result = transacaoController.buscarTransacoesPorCliente(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(transacaoService, times(1)).buscarTransacoesPorCliente(anyLong());
    }
}