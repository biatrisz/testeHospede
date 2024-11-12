package com.teste.hospede.entitie;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProdutoTest {
	private Produto produto;
	@BeforeEach
	void setUp() {
		produto = new Produto (1L, "secador de cabelo", "300");
	}
	@Test
	@DisplayName("Testando o getter e setter do campo ID")
	void testGetSetId() {
		// Act
		produto.setId(1L);
		// Assert
		assertEquals(1L, produto.getId());
	}

	@Test
	@DisplayName("Testando o getter e setter do campo num")
	void testGetSetNome() {
		// Act
		produto.setNome("italo");
		// Assert
		assertEquals("italo", produto.getNome());
	}

	@Test
	@DisplayName("Testando o getter e setter do campo tipo")
	void testGetSetPreco() {
		// Act
		produto.setPreco("000");
		// Assert
		assertEquals("000", produto.getPreco());
	}

	@Test
	@DisplayName("Testando o construtor com todos os argumentos")
	void testProdutoConstructor() {
		Produto novoproduto = new Produto(1L,"secador de cabelo", "300");
		assertAll("novoproduto",
				()-> assertEquals(1L, novoproduto.getId()),
				()-> assertEquals("secador de cabelo", novoproduto.getNome()),
				()-> assertEquals("300", novoproduto.getPreco()));
		
}

}
