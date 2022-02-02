package com.dio.jpa.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.jpa.model.Carro;
import com.dio.jpa.model.Cliente;
import com.dio.jpa.model.Multa;
import com.dio.jpa.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> clientes(@PathVariable Integer id) {

		Cliente cliente = clienteRepository.findClienteById(id);
		Carro carro = cliente.getCarros().stream().findFirst().orElse(new Carro());
		carro.setCliente(null);
		Multa multa = carro.getMultas().stream().findFirst().orElse(new Multa());
		multa.setCarro(null);

		return ResponseEntity.ok(cliente);

	}

	@GetMapping("/salvar")
	public ResponseEntity<Void> clintes() {

		Carro carro = new Carro();
		carro.setMarca("Ford");
		carro.setModelo("Fusion");

		Multa multa = new Multa();
		multa.setValor(100.00);
		multa.setData(LocalDateTime.now());
		multa.setCarro(carro);

		Cliente cliente = new Cliente();
		cliente.setNome("Joao");
		cliente.setCarros((List<Carro>) Collections.singleton(carro));
		carro.setCliente(cliente);
		carro.setMultas((List<Multa>) Collections.singleton(multa));
		cliente.setCarros((List<Carro>) Collections.singleton(carro));
		clienteRepository.save(cliente);

		return ResponseEntity.ok().body(null);

	}

}
