package com.uva.a4.sisesp.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uva.a4.sisesp.dto.DtoIngrediente;
import com.uva.a4.sisesp.model.Ingrediente;
import com.uva.a4.sisesp.repository.IngredienteRepository;

@Service

public class IngredienteService {

//	@Autowired
	IngredienteRepository repository;
	
	@Autowired
	 private ModelMapper modelMapper;
	
	public IngredienteService(IngredienteRepository repository) {
		this.repository = repository;
	}
	
	
	public List<DtoIngrediente> listar() {
		
		List<Ingrediente> lista = repository.findAll();
		
		List<DtoIngrediente> listDto = mapListToDtos(lista);
		
		
		return listDto;
	}
	
	public boolean salvar(DtoIngrediente dto) {
		
		 Ingrediente ingrediente = modelMapper.map(dto, Ingrediente.class);
		
		//Ingrediente obj = new Ingrediente(dto.getId(), dto.getNome());
		
		repository.save(ingrediente);
		
		return true;
	}
	
	
	public boolean deletar(DtoIngrediente dto) {
			
			Ingrediente obj = new Ingrediente(dto.getId(), dto.getNome());
			
			repository.delete(obj);
			
			return true;
		}
	
	public boolean alterar(DtoIngrediente dto) {
		
		Optional<Ingrediente> opt =  repository.findById(dto.getId());
		
		Ingrediente ingrediente = opt.get();
		
		ingrediente.setNome(dto.getNome());
		
		repository.save(ingrediente);
		
		return true;
	}
	
	 public List<DtoIngrediente> mapListToDtos(List<Ingrediente> ingredientes) {
	        return modelMapper.map(ingredientes, new TypeToken<List<DtoIngrediente>>() {}.getType());
	    }
}
