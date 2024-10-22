package com.uva.a4.sisesp.service;

import com.uva.a4.sisesp.dto.DtoPrato;
import com.uva.a4.sisesp.dto.request.RequestPrato;

import com.uva.a4.sisesp.model.Ingrediente;
import com.uva.a4.sisesp.model.Prato;
import com.uva.a4.sisesp.repository.IngredienteRepository;
import com.uva.a4.sisesp.repository.PratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PratoService {

    @Autowired
    private PratoRepository pratoRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public DtoPrato createPrato(RequestPrato requestPrato) {
        Prato prato = new Prato();
        prato.setNome(requestPrato.getNome());

        List<Ingrediente> ingredientes = requestPrato.getIngredientes().stream()
                .map(ingredienteId -> {
                    return ingredienteRepository.findById(ingredienteId)
                            .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado: " + ingredienteId));
                })
                .collect(Collectors.toList());

        prato.setIngredientes(ingredientes);

        Prato savedPrato = pratoRepository.save(prato);

        return DtoPrato.fromPrato(savedPrato);
    }

    public List<DtoPrato> getAllPratos() {
        List<Prato> pratos = pratoRepository.findAll();
        return pratos.stream()
                .map(DtoPrato::fromPrato)
                .collect(Collectors.toList());
    }

    public DtoPrato findById(long id) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado: " + id));
        return DtoPrato.fromPrato(prato);
    }

    public DtoPrato updatePrato(RequestPrato requestPrato, long id) {
        Prato existingPrato = pratoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Prato não encontrado"));

        List<Ingrediente> ingredientes = requestPrato.getIngredientes().stream()
                .map(ingredienteId -> ingredienteRepository.findById(ingredienteId)
                        .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado: " + ingredienteId)))
                .collect(Collectors.toList());

        existingPrato.setIngredientes(ingredientes);

        Prato updatedPrato = pratoRepository.save(existingPrato);

        return DtoPrato.fromPrato(updatedPrato);
    }


    public void deletar(long id) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Prato not Found"));
        pratoRepository.delete(prato);
    }
}
