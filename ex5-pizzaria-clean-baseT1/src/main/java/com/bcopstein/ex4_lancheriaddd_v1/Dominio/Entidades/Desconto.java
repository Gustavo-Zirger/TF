package com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades;

import java.util.function.BiFunction;

public class Desconto {
    private String identificador;
    private BiFunction<Double,Integer,Double> calculaDesconto;

    public Desconto(String id,BiFunction<Double,Integer,Double> calculo){
        this.identificador = id;
        this.calculaDesconto = calculo;
    }

    
    public String getId(){
        return identificador;
    }
    public double calculaDesconto(double valor,int pedidosRecentes){
        return calculaDesconto.apply(valor,pedidosRecentes);
    }
}