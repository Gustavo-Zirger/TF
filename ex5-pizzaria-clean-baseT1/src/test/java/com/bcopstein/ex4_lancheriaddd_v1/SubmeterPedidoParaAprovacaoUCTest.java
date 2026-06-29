package com.bcopstein.ex4_lancheriaddd_v1;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.bcopstein.ex4_lancheriaddd_v1.Aplicacao.Requests.ItemPedidoRequest;
import com.bcopstein.ex4_lancheriaddd_v1.Aplicacao.Requests.PedidoRequest;
import com.bcopstein.ex4_lancheriaddd_v1.Aplicacao.SubmeterPedidoParaAprovacaoUC;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Pedido;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos.PedidoService;

class SubmeterPedidoParaAprovacaoUCTest {

    // Casos de teste:
    // 1. Deve encaminhar pedido ao serviço de pedidos e retornar o resultado.
    // 2. Deve propagar exceção quando o serviço de pedidos falhar.

    @Mock
    private PedidoService pedidoService;

    private SubmeterPedidoParaAprovacaoUC submeterPedidoUC;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        submeterPedidoUC = new SubmeterPedidoParaAprovacaoUC(pedidoService);
    }

    @Test
    @DisplayName("Deve submeter pedido para aprovação via UC")
    void deveSubmeterPedidoParaAprovacao() {
        ItemPedidoRequest item = new ItemPedidoRequest();
        item.setProdutoId(1L);
        item.setQuantidade(1);

        PedidoRequest request = new PedidoRequest();
        request.setClienteCpf("12345678900");
        request.setItens(List.of(item));

        Pedido esperado = null;
        when(pedidoService.submeterPedidoParaAprovacao("12345678900", request.getItens())).thenReturn(esperado);

        Pedido resultado = submeterPedidoUC.run(request);

        assertEquals(esperado, resultado);
        verify(pedidoService).submeterPedidoParaAprovacao("12345678900", request.getItens());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o serviço de pedidos falhar")
    void devePropagarExcecaoDoServicoDePedido() {
        ItemPedidoRequest item = new ItemPedidoRequest();
        item.setProdutoId(1L);
        item.setQuantidade(1);

        PedidoRequest request = new PedidoRequest();
        request.setClienteCpf("12345678900");
        request.setItens(List.of(item));

        when(pedidoService.submeterPedidoParaAprovacao("12345678900", request.getItens()))
                .thenThrow(new IllegalArgumentException("Cliente não encontrado"));

        assertThrows(IllegalArgumentException.class, () -> submeterPedidoUC.run(request));
    }
}
