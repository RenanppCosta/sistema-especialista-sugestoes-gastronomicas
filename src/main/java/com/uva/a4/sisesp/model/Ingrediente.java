package com.uva.a4.sisesp.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingredientes") // Define o nome da tabela no banco de dados
public class Ingrediente implements Serializable {

	private static final long serialVersionUID = 7988972443891325412L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática de IDs
	private Long id;

	@Column(nullable = false)
	private String nome;

}
