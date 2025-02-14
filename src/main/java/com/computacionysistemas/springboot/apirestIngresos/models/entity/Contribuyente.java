package com.computacionysistemas.springboot.apirestIngresos.models.entity;

import java.io.Serializable;

import java.util.ArrayList;
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


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

@Entity
@Table(name="contribuyentes")
public class Contribuyente implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long  idContribuyente;
	@Column(unique=true)
	@NotBlank(message=" No puede estar vacia ")
	@Size(min=5 , max=9 , message="El numero de tener al menos 4 digitos ")
	private String cedula;
	@NotBlank(message=" no puede estar vacio ")
	private String nombre;
	@NotBlank(message=" El Apellido no puede  estar  vacio ")
	private String apellido;
	@NotBlank 
	@Column(name="telefono")
	private String telefono; 
	
	//@NotNull
	@Email
	@Size(max=50)
	private String email;
	
	@NotBlank
	@Size(min=20, max=200, message = "debe  tener como minimo 20 y como maximo 200 caracteres")
	private String calle;
	
	private String casa;

	private String edificio;
	@Size(max=8)
	private String piso;
	@Size(max=8)
	private String apto; 
	
 	   
	@OneToMany(fetch=FetchType.LAZY,mappedBy="contribuyente", cascade= CascadeType.ALL)
	private  List<Ingreso> ingresos;
	
	public Contribuyente() {
		this.ingresos = new ArrayList<>(); 
	}
	
	public Long getidContribuyente() {
		return idContribuyente;
	}

	public void setidContribuyente(Long idencont) {
		this.idContribuyente = idencont;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombcont) {
		this.nombre= nombcont;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String ceducont) {
		this.cedula = ceducont;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telfcont) {
		this.telefono = telfcont;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apelcont) {
		this.apellido = apelcont;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getApto() {
		return apto;
	}
	public void setApto(String apto) {
		this.apto = apto;
	}

	public List<Ingreso> getIngresos() {
		return ingresos;
	}
	public void setIngresos(List<Ingreso> ingresos) {
		this.ingresos = ingresos;
	}
	public String getCasa() {
		return casa;
	}
	public void setCasa(String casa) {
		this.casa = casa;
	}
	public String getEdificio() {
		return edificio;
	}
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	public void  setCalle(String calles) {
		this.calle=calles;
	}
	
	
	public String getCalle() {
		return calle;
	}
	
	
	private static final long serialVersionUID = 1L;

}
