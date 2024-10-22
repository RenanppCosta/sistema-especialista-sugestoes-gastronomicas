package com.uva.a4.sisesp.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.uva.a4.sisesp.dto.DtoIngrediente;
import com.uva.a4.sisesp.model.Ingrediente;
import com.uva.a4.sisesp.repository.IngredienteRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IngredienteService {

    private final IngredienteRepository repository;

    public IngredienteService(IngredienteRepository repository) {
        this.repository = repository;
    }

    public DtoIngrediente salvar(DtoIngrediente dto) {
        Ingrediente ingrediente = dto.toIngrediente();
        ingrediente = repository.save(ingrediente);
        return DtoIngrediente.fromIngrediente(ingrediente);
    }

    public List<Ingrediente> findAll() {
        return repository.findAll();
    }

    public DtoIngrediente findById(long id) {
        Ingrediente ingrediente = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ingrediente not found with id: " + id));
        return DtoIngrediente.fromIngrediente(ingrediente);
    }

    public DtoIngrediente updateIngrediente(DtoIngrediente dtoIngrediente, long id) {
        Ingrediente existingIngrediente = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ingrediente not found"));
        existingIngrediente.setNome(dtoIngrediente.getNome());
        existingIngrediente = repository.save(existingIngrediente);
        return DtoIngrediente.fromIngrediente(existingIngrediente);
    }

    @Transactional
    public void delete(long id) {
        Ingrediente ingrediente = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ingrediente not found"));
        repository.delete(ingrediente);
    }
}
