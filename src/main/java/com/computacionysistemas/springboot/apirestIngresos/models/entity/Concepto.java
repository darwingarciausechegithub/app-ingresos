package com.computacionysistemas.springboot.apirestIngresos.models.entity;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="conceptos")
public class Concepto  implements Serializable{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private  int idConcepto;
	
	@Size(max=150)
	private  String descripcion;
	 
	@Column(columnDefinition = "text")
	private  String detalle;
	 
	private  int idFormula;
	 
	@Size(max=30)
	private  String codPartida;

	public int getIdConcepto() {
		return idConcepto;
	}

	public void setIdConcepto(int idConcepto) {
		this.idConcepto = idConcepto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public int getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(int idFormula) {
		this.idFormula = idFormula;
	}

	public String getCodPartida() {
		return codPartida;
	}

	public void setCodPartida(String codPartida) {
		this.codPartida = codPartida;
	}
	
	 @OneToMany(mappedBy="concepto",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
     private List<DetIngreso> detingreso;
	 
	 private static final long serialVersionUID = 1L;

}
