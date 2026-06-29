package com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Dados;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ITENSESTOQUE")
public class ItemEstoqueEntity {
    
    @Id
    private Long id;
    @Column(name = "ingrediente_id")
    private Long ingredienteId;
    
    private int quantidade;

    protected ItemEstoqueEntity() {}

    public ItemEstoqueEntity(Long id, Long ingredienteId, int quantidade) {
        this.id = id;
        this.ingredienteId = ingredienteId;
        this.quantidade = quantidade;
    }

    public Long getId(){ return id; }
    public Long getIngredienteId() { return ingredienteId; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}