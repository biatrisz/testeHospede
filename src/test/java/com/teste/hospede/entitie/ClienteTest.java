package com.teste.hospede.entitie;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClienteTest {
	private Cliente cliente;
	@BeforeEach
	@Test
	void setUp() {
		cliente = new Cliente (1, "italo", "39483-333", "999999", "9889");
	}
	@Test
	@DisplayName("Testando o getter e setter do campo ID")
	void testGetSetId() {
		//Act
		cliente.setId(2);
		//Assert
		assertEquals(2,cliente.getId());
	}
	@Test
	@DisplayName("Testando o getter e setter do campo nome")
	void testGetSetNome () {
		//Act
		cliente.setNome("fernando isaac");
		//Assert
		assertEquals("fernando isaac", cliente.getNome());
	}
	@Test
	@DisplayName("Testando o getter e setter do campo email")
	void testGetSetTelefone () {
		//Act
		cliente.setTelefone("9090-0909");
		//Assert
		assertEquals("9090-0909", cliente.getTelefone());

	}@Test
	@DisplayName("Testando o getter e setter do campo telefone")
	void testGetSetCpf () {
		//Act
		cliente.setCpf("0444440404");
		//Assert
		assertEquals("0444440404", cliente.getCpf());
	
	}@Test
	@DisplayName("Testando o getter e setter do campo telefone")
	void testGetSetRg () {
		//Act
		cliente.setRg("0444440404");
		//Assert
		assertEquals("0444440404", cliente.getRg());
}
@Test
@DisplayName("Testando o construtor com todos os argumentos")
void testClienteConstructor() {
	Cliente novoCliente = new Cliente(1,"lana", "2323-3423", "333333", "11111");
	assertAll("novoCliente",()-> assertEquals(3L, novoCliente.getId()),
			()-> assertEquals("lana", novoCliente.getNome()),
			()-> assertEquals("2323-3423", novoCliente.getTelefone()),
			()-> assertEquals("333333", novoCliente.getCpf()),
			()-> assertEquals("11111", novoCliente.getRg()));
}

}
