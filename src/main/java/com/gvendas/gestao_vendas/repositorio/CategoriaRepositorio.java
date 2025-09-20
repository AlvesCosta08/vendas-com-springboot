package com.gvendas.gestao_vendas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvendas.gestao_vendas.entidades.Categoria;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
    
}
