package com.bcopstein.ex4_lancheriaddd_v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Desconto;

@DisplayName("Teste das estratégias de desconto")
class DescontoTest {

    // Casos de teste:
    // 1. Desconto base 10% deve calcular 10% do valor.
    // 2. Desconto fidelidade 7% deve ser aplicado apenas quando pedidos recentes > 3.
    // 3. Desconto fidelidade não deve ser aplicado quando pedidos recentes <= 3.

    @Test
    @DisplayName("Desconto base 10% calcula corretamente")
    void deveCalcularDescontoBase10Porcento() {
        Desconto desconto = new Desconto("1", "Desconto base 10%.", (valor, qtd) -> valor * 0.10);
        assertEquals(10.0, desconto.calculaDesconto(100.0, 0));
    }

    @Test
    @DisplayName("Desconto fidelidade 7% aplicado para mais de 3 pedidos recentes")
    void deveAplicarDescontoFidelidadeQuandoMaisDeTresPedidosRecentes() {
        Desconto desconto = new Desconto("2", "Desconto fidelidade 7%.", (valor, qtdPedidos) -> {
            if (qtdPedidos > 3) {
                return valor * 0.07;
            }
            return 0.0;
        });
        assertEquals(7.0, desconto.calculaDesconto(100.0, 4), 1e-9);
    }

    @Test
    @DisplayName("Desconto fidelidade não aplicado para 3 ou menos pedidos recentes")
    void naoDeveAplicarDescontoFidelidadeQuandoTresOuMenosPedidosRecentes() {
        Desconto desconto = new Desconto("2", "Desconto fidelidade 7%.", (valor, qtdPedidos) -> {
            if (qtdPedidos > 3) {
                return valor * 0.07;
            }
            return 0.0;
        });
        assertEquals(0.0, desconto.calculaDesconto(100.0, 3));
        assertEquals(0.0, desconto.calculaDesconto(100.0, 2));
    }
}
