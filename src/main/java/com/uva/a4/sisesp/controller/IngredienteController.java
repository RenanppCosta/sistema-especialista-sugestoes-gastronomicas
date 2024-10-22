package com.uva.a4.sisesp.controller;


import com.uva.a4.sisesp.model.Ingrediente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uva.a4.sisesp.dto.DtoIngrediente;
import com.uva.a4.sisesp.service.IngredienteService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/ingrediente")
@Tag(name = "Ingrediente Controller", description = "Exemplo de documentação de API")
public class IngredienteController {


    @Autowired
    IngredienteService service;

    @GetMapping("/listar")
    public ResponseEntity<List<DtoIngrediente>> getAll() {
        List<Ingrediente> ingredienteList = service.findAll();
        List<DtoIngrediente> dtoIngredienteList = ingredienteList.stream()
                .map(ingrediente -> {
                    DtoIngrediente requestIngrediente = new DtoIngrediente();
                    requestIngrediente.setId(ingrediente.getId());
                    requestIngrediente.setNome(ingrediente.getNome());
                    return requestIngrediente;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoIngredienteList);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DtoIngrediente> findById(@PathVariable long id) {
        DtoIngrediente dtoIngrediente = service.findById(id);
        return ResponseEntity.ok(dtoIngrediente);
    }

    @PostMapping("/criar")
    public ResponseEntity<DtoIngrediente> createIngrediente(@RequestBody DtoIngrediente dtoIngrediente) {
        DtoIngrediente ingredienteCriado = service.salvar(dtoIngrediente);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoIngrediente> updateIngrediante(@RequestBody @Valid DtoIngrediente dtoIngrediente, @PathVariable long id) {
        DtoIngrediente updateIngradiente = service.updateIngrediente(dtoIngrediente, id);
        return ResponseEntity.ok(updateIngradiente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DtoIngrediente> deleteIngrediante(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

