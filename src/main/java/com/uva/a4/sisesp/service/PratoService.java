package com.uva.a4.sisesp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uva.a4.sisesp.dto.DtoIngrediente;
import com.uva.a4.sisesp.dto.DtoPrato;
import com.uva.a4.sisesp.model.Ingrediente;
import com.uva.a4.sisesp.model.Prato;
import com.uva.a4.sisesp.repository.IngredienteRepository;
import com.uva.a4.sisesp.repository.PratoRepository;

@Service

public class PratoService {

//	@Autowired
	PratoRepository repository;

	@Autowired
	IngredienteRepository ingredienteRepository;

	@Autowired
	private ModelMapper modelMapper;

	public PratoService(PratoRepository repository) {
		this.repository = repository;
	}

	public List<DtoPrato> listar() {

		List<Prato> lista = repository.findAll();

		List<DtoPrato> listDto = mapListToDtos(lista);

		return listDto;
	}

	public boolean salvar(DtoPrato dto) {

		Prato prato = modelMapper.map(dto, Prato.class);
		
		repository.save(prato);

		return true;
	}

	public boolean deletar(DtoPrato dto) {

		// Prato obj = new Prato(dto.getId(), dto.getNome(), null);

		repository.deleteById(dto.getId());

		return true;
	}

	public boolean alterar(DtoPrato dto) {

		Optional<Prato> opt = repository.findById(dto.getId());

		Prato prato = opt.get();

		prato.setNome(dto.getNome());

		List<Ingrediente> lista = new ArrayList<Ingrediente>();

		//Iterando sobre uma lista de obetos do tipo DtoIngrediente
		for (DtoIngrediente ingrediente : dto.getIngredientes()) {
			Optional<Ingrediente> optIngrediente = ingredienteRepository.findById(ingrediente.getId());

			lista.add(optIngrediente.get());
		}

		prato.setIngredientes(lista);

		repository.save(prato);

		return true;
	}

	public List<DtoPrato> mapListToDtos(List<Prato> pratos) {
		return modelMapper.map(pratos, new TypeToken<List<DtoPrato>>() {
		}.getType());
	}
}
