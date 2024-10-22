package com.uva.a4.sisesp.controller;

import java.util.List;

import com.uva.a4.sisesp.dto.DtoPrato;
import com.uva.a4.sisesp.dto.request.RequestPrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uva.a4.sisesp.service.PratoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController()
@RequestMapping(path = "/prato", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Prato Controller", description = "Exemplo de documentação de API")
public class PratoController {


    @Autowired
    PratoService service;

    @GetMapping("/listar")
    public ResponseEntity<List<DtoPrato>> getAll() {
        List<DtoPrato> dtoPratos = service.getAllPratos();
        return ResponseEntity.ok(dtoPratos);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DtoPrato> findById(@PathVariable long id) {
        DtoPrato dtoPrato = service.findById(id);
        return ResponseEntity.ok(dtoPrato);
    }

    @PostMapping("/criar")
    public ResponseEntity<DtoPrato> createPrato(@RequestBody RequestPrato requestPrato) {
        DtoPrato responsePrato = service.createPrato(requestPrato);
        return ResponseEntity.ok(responsePrato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoPrato> updatePrato(
            @PathVariable Long id,
            @RequestBody RequestPrato requestPrato) {

        DtoPrato updatedPrato = service.updatePrato(requestPrato, id);
        return ResponseEntity.ok(updatedPrato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrato(@PathVariable long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
