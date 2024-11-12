package com.teste.hospede.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.hospede.entitie.Hospede;
import com.teste.hospede.repository.HospedeRepository;

@Service
public class HospedeService {
	
	@Autowired
	private HospedeRepository hospederepository;
	
	public Hospede salvarHospede (Hospede hospede)	{
		return hospederepository.save(hospede);
		}
	public List<Hospede> listarTodos(){
		return hospederepository.findAll();
	}
	public Optional<Hospede> buscarPorId(Long id){
		return hospederepository.findById(id);
	}
	public Hospede atualizarHospede (Hospede hospede) {
		if (hospederepository.existsById(hospede.getId())) {
			return hospederepository.save(hospede);
		}else{
			throw new RuntimeException("Hóspede não encontrado");
		}
	}
	public void deletarHospede(Long id) {
		hospederepository.deleteById(id);
	}
}
