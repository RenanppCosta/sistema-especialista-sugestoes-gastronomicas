package com.uva.a4.sisesp.dto;

import com.uva.a4.sisesp.model.Ingrediente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoIngrediente {

	private Long id;
	private String nome;


	public Ingrediente toIngrediente() {
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setNome(this.nome);
		return ingrediente;
	}

	public static DtoIngrediente fromIngrediente(Ingrediente ingrediente) {
		return new DtoIngrediente(
				ingrediente.getId(),
				ingrediente.getNome()
		);
	}
}

