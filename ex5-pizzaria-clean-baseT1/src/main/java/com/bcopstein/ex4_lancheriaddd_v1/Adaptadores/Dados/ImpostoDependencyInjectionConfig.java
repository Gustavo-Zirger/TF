package com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Dados;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Imposto;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos.ServicoImposto;

@Configuration
public class ImpostoDependencyInjectionConfig {

    @Bean
    public ServicoImposto servicoImposto() {
        Map<String, Imposto> leis = new HashMap<>();

        leis.put("0412/2022", new Imposto("0412/2022", valor -> valor * 0.10));
        leis.put("5762/2026", new Imposto("5762/2026", valor -> {
            if (valor <= 50.0) return 0.0;
            return valor * 0.15;
        }));

        return new ServicoImposto(leis);
    }
}