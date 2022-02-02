package com.dio.jpa.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dio.jpa.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@EntityGraph(attributePaths = {"carros", "carros.multas"})
	Cliente	findClienteById(Integer Id);
}
