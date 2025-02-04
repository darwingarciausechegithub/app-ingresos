package com.computacionysistemas.springboot.apirestIngresos.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

 
@Entity
@Table(name="partidas")
public class Partida {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private  int idPartida;
	@Size(max=30)
	private String codigoPartida;
	@Column(columnDefinition="text")
	private String  nombrePartida;

}
