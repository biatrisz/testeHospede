package com.teste.hospede.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.hospede.entitie.Produto;

public interface ProdutoRepository extends JpaRepository <Long, Produto>{

}
