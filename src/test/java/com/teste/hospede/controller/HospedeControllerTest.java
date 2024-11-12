package com.teste.hospede.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.teste.hospede.entitie.Hospede;
import com.teste.hospede.repository.HospedeRepository;
import com.teste.hospede.service.HospedeService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class HospedeControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private HospedeRepository hospedeRepository;
	@Autowired
	private HospedeService hospedeService;

	@BeforeEach
	void setUp() {
		hospedeRepository.deleteAll();
	}

	@Test
	@DisplayName("Teste de criação de hóspede")
	void testCriarHospede() {
		Hospede hospede = new Hospede(null, "Bea", "Bea@gmail.com", "(00)0000-0000");
		ResponseEntity<Hospede> response = restTemplate.postForEntity("/api/hospedes", hospede, Hospede.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Julia Maria", response.getBody().getNome());

	}

	@Test
	@DisplayName("Teste de listagem de todos os hóspedes")
	void testListarTodosHóspedes() {
		Hospede hospede1 = new Hospede(null, "Bea", "Bea@gmail.com", "(00)0000-0000");
		Hospede hospede2 = new Hospede(null, "Del Rey", "@LanaDelRey@gmail.com", "(00) 0000-0000");

		hospedeService.salvarHospede(hospede1);
		hospedeService.salvarHospede(hospede2);

		ResponseEntity<Hospede[]> response = restTemplate.getForEntity("/api/hospedes", Hospede[].class);

		assertEquals(HttpStatus.OK, response.getStatusCode(), "A resposta deveria ser 200 blz");
		assertNotNull(response.getBody(), "o corpo da resposta ñ deveria ser nulo");
		assertEquals(2, response.getBody().length, "a quantidade de hóspedes retornada deveria ser 2");

	}

	@Test
	@DisplayName("Teste de buscar hóspede por ID")
	void testBuscarHospedePorId() {
		Hospede hospede = new Hospede(null, "Bea", "Bea@gmail.com", "(00)0000-0000");
		
		Hospede hospedeSalvo = hospedeRepository.save(hospede);
		ResponseEntity<Hospede> response = restTemplate.getForEntity("/api/hospedes" + hospedeSalvo.getId(),Hospede.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Bea", response.getBody().getNome());
	}
	@Test
	@DisplayName("Teste de atualizar hóspede por ")
	void testAtualizarHospede() {
		Hospede hospedeSalvo = hospedeRepository.save(new Hospede(null, "Bea", "Bea@gmail.com", "(00) 0000-0000"));
		
		Hospede hospedeAtualizado = new Hospede (hospedeSalvo.getId(), "Bea", "Bea@Gmail.com", "(00)0000-0000");
		
		HttpEntity<Hospede>requestUpdate = new HttpEntity<>(hospedeAtualizado);
		ResponseEntity<Hospede> response =restTemplate.exchange("/api/hospedes/" + hospedeSalvo.getId(),HttpMethod.PUT, requestUpdate, Hospede.class );
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Bea", response.getBody().getNome());
		assertEquals("Bea@gmail.com", response.getBody().getEmail());
	}
	@Test
	@DisplayName("Teste para deletar hospede")
	void testDeletarHospede() {
		Hospede hospede = new Hospede(null, "Bea", "Bea@gmail.com", "(00) 0000-0000");
		Hospede hospedeSalvo = hospedeService.salvarHospede(hospede);
		
		ResponseEntity<Void> response =restTemplate.exchange("/api/hospedes/" + hospedeSalvo.getId(),HttpMethod.DELETE, null,void.class);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), "a resposta deveria ser 204 no content");
		
		ResponseEntity<Hospede> checkDeleted = restTemplate.getForEntity("/api/hospedes" + hospedeSalvo.getId(),Hospede.class);
				assertEquals(HttpStatus.NOT_FOUND, checkDeleted.getStatusCode(),
						"Após o delete o hospede ñ deveria ser encontrado ");
		
	}
}
