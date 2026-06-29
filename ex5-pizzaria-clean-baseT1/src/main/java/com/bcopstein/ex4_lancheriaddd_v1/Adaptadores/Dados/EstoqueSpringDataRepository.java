package com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Dados;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueSpringDataRepository extends JpaRepository<ItemEstoqueEntity, Long> {
    Optional<ItemEstoqueEntity> findByIngredienteId(long ingredienteId);
}