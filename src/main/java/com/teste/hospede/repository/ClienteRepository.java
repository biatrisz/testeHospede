package com.teste.hospede.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.hospede.entitie.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long>{

}
