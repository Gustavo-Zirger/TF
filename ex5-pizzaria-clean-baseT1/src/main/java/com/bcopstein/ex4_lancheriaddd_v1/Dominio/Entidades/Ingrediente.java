package com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredientes")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;

    protected Ingrediente(){}

    public Ingrediente(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public long getId() { return id; }
    public String getDescricao() { return descricao; }
}
