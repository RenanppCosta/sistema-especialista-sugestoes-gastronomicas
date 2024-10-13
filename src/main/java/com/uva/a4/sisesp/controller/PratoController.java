package com.uva.a4.sisesp.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uva.a4.sisesp.dto.DtoPrato;
import com.uva.a4.sisesp.service.PratoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController()
@RequestMapping(path = "/prato", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Prato Controller", description = "Exemplo de documentação de API")
public class PratoController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	PratoService service;

	@PostMapping("/criar")
	public DtoPrato criarPrato(@RequestBody DtoPrato dtoPrato) {

		boolean ok = service.salvar(dtoPrato);
		return modelMapper.map(dtoPrato, DtoPrato.class);
	}

	@GetMapping("/Listar")
	public List<DtoPrato> getAll() {

		List<DtoPrato> listaDto = service.listar();
		
		return listaDto;
	}
	
	@PutMapping("/alterar")
	public DtoPrato alterarPrato(@RequestBody DtoPrato dtoPrato) {

		boolean ok = service.salvar(dtoPrato);
		return modelMapper.map(dtoPrato, DtoPrato.class);
	}
	
	@DeleteMapping("/deletar")
	public DtoPrato deletarPrato(@RequestBody DtoPrato dtoPrato) {

		boolean ok = service.deletar(dtoPrato);
		return modelMapper.map(dtoPrato, DtoPrato.class);
	}
}
