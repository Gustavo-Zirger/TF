package com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Desconto;

@Service
public class ServicoDesconto {

    private String desconto;
    private Map<String, Desconto> regra;
    
    @Autowired
    public ServicoDesconto(String id) {
        this.desconto = id;
        this.regra = new HashMap<>();
        // criada regras de desconto, mas falta aplicar a regra certa no 1
        regra.put("1", new Desconto("1", valor -> valor * 0.07));
        regra.put("2", new Desconto("2", valor -> valor * 0.1));           
    }

    public double calcular(double valorDaVenda) {
        Desconto desconto = regra.get(this.desconto);
        if (desconto == null) {
            throw new IllegalArgumentException("Desconto não encontrado: " + this.desconto);
        }
        return desconto.calculaDesconto(valorDaVenda);
    }
}
