package com.bcopstein.ex4_lancheriaddd_v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos.ServicoImposto;

@DisplayName("Testes unitários do ServicoImposto")
class ServicoImpostoTest {

    // Casos de teste:
    // 1. Lei 0412/2022 deve aplicar 10% sobre o valor.
    // 2. Lei 5762/2026 deve retornar 0 para valores <= 50.
    // 3. Lei 5762/2026 deve aplicar 15% para valores > 50.
    // 4. Deve lançar IllegalArgumentException para lei inexistente.

    private ServicoImposto servicoImposto;

    @BeforeEach
    void prepararCenario() {
        // Configura o padrão para cada teste individual.
        String leiVigente = "0412/2022";
        servicoImposto = new ServicoImposto(leiVigente);
    }

    @Test
    @DisplayName("Deve calcular imposto de 10% para a lei 0412/2022")
    void calcularImpostoLei04122022() {
        double resultado = servicoImposto.calcular(100.0);
        assertEquals(10.0, resultado);
    }

    @Test
    @DisplayName("Deve calcular imposto zero para valor até 50 na lei 5762/2026")
    void calcularImpostoLei57622026ValorMenorOuIgual50() {
        servicoImposto = new ServicoImposto("5762/2026");
        double resultado = servicoImposto.calcular(50.0);
        assertEquals(0.0, resultado);
    }

    @Test
    @DisplayName("Deve calcular imposto de 15% para valor acima de 50 na lei 5762/2026")
    void calcularImpostoLei57622026ValorMaior50() {
        servicoImposto = new ServicoImposto("5762/2026");
        double resultado = servicoImposto.calcular(100.0);
        assertEquals(15.0, resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção para lei de imposto inexistente")
    void lancarExcecaoParaLeiInexistente() {
        servicoImposto = new ServicoImposto("9999/9999");
        assertThrows(IllegalArgumentException.class, () -> servicoImposto.calcular(100.0));
    }
}
