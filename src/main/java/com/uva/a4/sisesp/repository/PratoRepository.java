package com.uva.a4.sisesp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uva.a4.sisesp.model.Prato;


public interface PratoRepository extends JpaRepository<Prato, Long> {
	
	Optional<Prato> findById(Long id);
	List<Prato> findAll();
	
	
}
