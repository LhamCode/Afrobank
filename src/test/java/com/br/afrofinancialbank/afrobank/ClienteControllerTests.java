package com.br.afrofinancialbank.afrobank;

import com.br.afrofinancialbank.afrobank.controller.ClienteController;
import com.br.afrofinancialbank.afrobank.dto.SaldoResponse;
import com.br.afrofinancialbank.afrobank.entity.Cliente;
import com.br.afrofinancialbank.afrobank.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteControllerTests {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarCliente() {
        Cliente cliente = new Cliente();
        when(clienteService.cadastrarCliente(any(Cliente.class))).thenReturn(cliente);

        Cliente result = clienteController.cadastrarCliente(cliente);

        assertNotNull(result);
        verify(clienteService, times(1)).cadastrarCliente(any(Cliente.class));
    }

    @Test
    void testLoginCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678903");
        cliente.setSenha("senha123");
        when(clienteService.loginCliente(anyString(), anyString())).thenReturn(cliente);

        Cliente result = clienteController.loginCliente(cliente);

        assertNotNull(result);
        verify(clienteService, times(1)).loginCliente(anyString(), anyString());
    }

    @Test
    void testAtualizarCliente() {
        Cliente cliente = new Cliente();
        when(clienteService.atualizarCliente(anyLong(), any(Cliente.class))).thenReturn(cliente);

        Cliente result = clienteController.atualizarCliente(1L, cliente);

        assertNotNull(result);
        verify(clienteService, times(1)).atualizarCliente(anyLong(), any(Cliente.class));
    }

    @Test
    void testBuscarPorCpf() {
        Cliente cliente = new Cliente();
        when(clienteService.buscarPorCpf(anyString())).thenReturn(cliente);

        Cliente result = clienteController.buscarPorCpf("12345678903");

        assertNotNull(result);
        verify(clienteService, times(1)).buscarPorCpf(anyString());
    }

    @Test
    void testConsultarSaldo() {
        SaldoResponse saldoResponse = new SaldoResponse();
        when(clienteService.consultarSaldo(anyLong())).thenReturn(saldoResponse);

        SaldoResponse result = clienteController.consultarSaldo(1L);

        assertNotNull(result);
        verify(clienteService, times(1)).consultarSaldo(anyLong());
    }

    @Test
    void testListarClientes() {
        List<Cliente> clientes = List.of(new Cliente());
        when(clienteService.listarClientes()).thenReturn(clientes);

        List<Cliente> result = clienteController.listarClientes();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(clienteService, times(1)).listarClientes();
    }
}