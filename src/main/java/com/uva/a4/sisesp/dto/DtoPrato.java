package com.uva.a4.sisesp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.uva.a4.sisesp.model.Ingrediente;
import com.uva.a4.sisesp.model.Prato;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAutoDetect
public class DtoPrato implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private List<DtoIngrediente> ingredientes = new ArrayList<>();

    public static DtoPrato fromPrato(Prato prato) {
        List<DtoIngrediente> dtoIngredientes = prato.getIngredientes().stream()
                .map(DtoIngrediente::fromIngrediente)
                .collect(Collectors.toList());

        DtoPrato dtoPrato = new DtoPrato();
        dtoPrato.setId(prato.getId());
        dtoPrato.setNome(prato.getNome());
        dtoPrato.setIngredientes(dtoIngredientes);
        return dtoPrato;
    }

    public Prato toPrato() {
        Prato prato = new Prato();
        prato.setId(this.id);
        prato.setNome(this.nome);

        List<Ingrediente> listaIngredientes = this.ingredientes.stream()
                .map(DtoIngrediente::toIngrediente)
                .collect(Collectors.toList());

        prato.setIngredientes(listaIngredientes);
        return prato;
    }
}
