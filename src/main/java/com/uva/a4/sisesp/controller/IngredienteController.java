package com.uva.a4.sisesp.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uva.a4.sisesp.dto.DtoIngrediente;
import com.uva.a4.sisesp.service.IngredienteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/ingrediente", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Ingrediente Controller", description = "Exemplo de documentação de API")
public class IngredienteController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	IngredienteService service;

	

	@PostMapping("/criar")
	public DtoIngrediente criarIngrediente(@RequestBody DtoIngrediente dtoIngrediente) {

		boolean ok = service.salvar(dtoIngrediente);
		return modelMapper.map(dtoIngrediente, DtoIngrediente.class);
	}

	@GetMapping("/Listar")
	public List<DtoIngrediente> getAll() {

		List<DtoIngrediente> listaDto = service.listar();
		
		return listaDto;
	}

}
