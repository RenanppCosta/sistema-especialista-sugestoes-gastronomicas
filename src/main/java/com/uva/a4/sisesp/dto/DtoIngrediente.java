package com.uva.a4.sisesp.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DtoIngrediente implements Serializable {
	
	private static final long serialVersionUID = 2185046478967831703L;
	private Long id;
	private String nome;
}
