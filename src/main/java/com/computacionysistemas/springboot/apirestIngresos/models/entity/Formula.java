package com.computacionysistemas.springboot.apirestIngresos.models.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="formulas")
public class Formula implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private long   idFormula;
	
    @NotBlank(message=" Descripcion no puede estar vacia ")
    @Size(min = 20 , max = 300 , message="la descripcion debe tener  como minimo 20 y com maximo 300 caracteres" )
	private String descripcion ;
    
    @Column(columnDefinition = "text")
    @NotBlank(message="imposible actualizar , el campo  formula  esta  vacio") 
	private String formula;

	public long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(long idFormula) {
		this.idFormula = idFormula;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}  
	private static final long serialVersionUID = 1L;
}
