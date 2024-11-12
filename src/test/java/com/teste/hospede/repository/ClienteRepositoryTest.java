package com.teste.hospede.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.teste.hospede.entitie.Cliente;

@DataJpaTest
class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;

	@DisplayName("Testando o Save")
	@Test
	void testSalvarRepository() {

		Cliente cliente = new Cliente(1, "Italo", "666-6666", "69509560", "859443");

		Cliente saveCliente = clienteRepository.save(cliente);

		assertNotNull(saveCliente);
		assertTrue(saveCliente.getId() > 0);

	}

	@DisplayName("Testando get para todos hospedes")
	@Test
	void testGetAllRepository() {

		Cliente cliente = new Cliente(1, "Italo", "666-6666", "69509560", "859443");

		clienteRepository.save(cliente);

		List<Long> clienteList = clienteRepository.findAll();

	}

	@DisplayName("Testando get by id")
	@Test
	void testGetById() {

		Cliente cliente = new Cliente(1, "Italo", "666-6666", "69509560", "859443");

		clienteRepository.save(cliente);

		Cliente saveCliente = clienteRepository.findById(cliente.getId()).get();

		assertNotNull(saveCliente);
		assertEquals(cliente.getId(), saveCliente.getId());
	}

	@DisplayName("Testando o update")
	@Test
	void testUpdateCliente() {

		Cliente cliente = new Cliente(1, "Italo", "666-6666", "69509560", "859443");

		clienteRepository.save(cliente);

		Cliente saveCliente = clienteRepository.findById(cliente.getId()).get();
		cliente.setNome("");
		cliente.setTelefone("fernandeswellington@gmail.com");
		cliente.setCpf("69509560");
		cliente.setRg("859443");

		Cliente updateCliente = clienteRepository.save(saveCliente);

		assertNotNull(updateCliente);
		assertEquals("Italo", updateCliente.getNome());
		assertEquals("666-6666", updateCliente.getTelefone());
		assertEquals("69509560", updateCliente.getCpf());
		assertEquals("859443", updateCliente.getRg());

	}

	@DisplayName("testando o delete")
	@Test
	void testDeleteCliente() {

		Cliente cliente = new Cliente(1, "Julio Victor", "(00) 0000-0000", "42343", "32432");
		clienteRepository.save(cliente);
		clienteRepository.deleteById(cliente.getId());
		Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getId());
		assertTrue(clienteOptional.isEmpty());

	}
}
