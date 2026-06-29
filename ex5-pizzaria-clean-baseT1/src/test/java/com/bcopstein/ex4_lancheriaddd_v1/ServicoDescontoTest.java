package com.bcopstein.ex4_lancheriaddd_v1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Dados.PedidoRepository;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Cliente;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Pedido;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos.ServicoDesconto;

@DisplayName("Testes unitários do ServicoDesconto")
class ServicoDescontoTest {

    // Casos de teste:
    // 1. Política padrão 10% deve ser aplicada ao valor da venda.
    // 2. Política de fidelidade deve aplicar 7% se cliente tiver mais de 3 pedidos recentes.
    // 3. Política de fidelidade não deve aplicar desconto quando tiver 3 ou menos pedidos recentes.
    // 4. Deve lançar exceção ao definir política inexistente.

    @Mock
    private PedidoRepository pedidoRepository;

    private ServicoDesconto servicoDesconto;
    private Cliente cliente;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        servicoDesconto = new ServicoDesconto(pedidoRepository);
        cliente = new Cliente("12345678900", "João Silva", "11999999999", null, null, "Rua A", "joao@email.com");
    }

    @Test
    @DisplayName("Deve aplicar desconto base 10%")
    void deveAplicarDescontoBase10Porcento() {
        when(pedidoRepository.recuperaTodos()).thenReturn(new ArrayList<>());

        double desconto = servicoDesconto.calcular(cliente, 200.0);

        assertEquals(20.0, desconto);
    }

    @Test
    @DisplayName("Deve aplicar desconto fidelidade 7% para mais de 3 pedidos recentes")
    void deveAplicarDescontoFidelidadeQuandoMaisDeTresPedidosRecentes() {
        servicoDesconto.defineDescontoCorrente("2");

        List<Pedido> pedidos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            pedidos.add(new Pedido(0L, cliente, LocalDateTime.now().minusDays(1), new ArrayList<>(), Pedido.Status.APROVADO, 100.0, 0.0, 0.0, 100.0));
        }
        when(pedidoRepository.recuperaTodos()).thenReturn(pedidos);

        double desconto = servicoDesconto.calcular(cliente, 1000.0);

        assertEquals(70.0, desconto);
    }

    @Test
    @DisplayName("Não deve aplicar desconto fidelidade para 3 pedidos recentes ou menos")
    void naoDeveAplicarDescontoFidelidadeQuandoTresOuMenosPedidosRecentes() {
        servicoDesconto.defineDescontoCorrente("2");

        List<Pedido> pedidos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            pedidos.add(new Pedido(0L, cliente, LocalDateTime.now().minusDays(1), new ArrayList<>(), Pedido.Status.APROVADO, 100.0, 0.0, 0.0, 100.0));
        }
        when(pedidoRepository.recuperaTodos()).thenReturn(pedidos);

        double desconto = servicoDesconto.calcular(cliente, 1000.0);

        assertEquals(0.0, desconto);
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException para política de desconto inexistente")
    void deveLancarParaPoliticaDeDescontoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> servicoDesconto.defineDescontoCorrente("999"));
    }
}
