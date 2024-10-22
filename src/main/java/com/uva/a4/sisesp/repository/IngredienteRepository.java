package com.uva.a4.sisesp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uva.a4.sisesp.model.Ingrediente;


public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

	Optional <Ingrediente> findById(Long id);
	List<Ingrediente> findAll();


}