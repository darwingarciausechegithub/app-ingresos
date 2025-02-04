package com.computacionysistemas.springboot.apirestIngresos.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="ingresos")
public class Ingreso implements  Serializable {
	
  
  //@GeneratedValue(strategy= GenerationType.IDENTITY)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingreso_seq")
  @SequenceGenerator(name = "ingreso_seq", sequenceName = "secuencia_ingreso", allocationSize = 1)
  private  Long    idIngreso;

  
  @Column(name="fecha_pago")
  //@Temporal(TemporalType.DATE)
  private  Date    fechPago;
  private  float   subTotal;
  private  float   porDescuento;
  private  float   montoDescuento;
  private  float   montoMulta;
  private  float   montoTotal;
  private  String  usuario;
  private  int     numeroCaja;
  private  String  conceptoIngreso;
  
  @Column(name="creado_at")
  @Temporal(TemporalType.TIMESTAMP)
  private  Date    createdAt;
  
  @OneToMany(mappedBy="ingreso",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
  private List<DetIngreso> detingresos;
  
  public Ingreso() {
	this.detingresos = new ArrayList<>();
}

  @PrePersist
  public  void  prePersist() {
	  this.createdAt= new Date();
  }
 
  public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}
 

public void setContribuyente(Contribuyente contribuyente) {
	this.contribuyente = contribuyente;
}

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="contribuyenteId")
  private Contribuyente contribuyente; 
  // Relacion Propietaria//  


public List<DetIngreso> getDetingresos() {
	return detingresos;
}

public void setDetingresos(List<DetIngreso> detingresos) {
	this.detingresos = detingresos;
}

public Double getTotalIngresos() {
	  Double totalIngreso=0.00;
	  for(DetIngreso item : detingresos ) {
		  totalIngreso += item.calcularTotalConcepto();
	  }
	  return totalIngreso ;
}

public float getSubTotal() {
	return subTotal;
}

public void setSubTotal(float subTotal) {
	this.subTotal = subTotal;
}

public float getPorDescuento() {
	return porDescuento;
}

public void setPorDescuento(float porDescuento) {
	this.porDescuento = porDescuento;
}

public float getMontoDescuento() {
	return montoDescuento;
}

public void setMontoDescuento(float montoDescuento) {
	this.montoDescuento = montoDescuento;
}

public float getMontoMulta() {
	return montoMulta;
}

public void setMontoMulta(float montoMulta) {
	this.montoMulta = montoMulta;
}

public float getMontoTotal() {
	return montoTotal;
}

public void setMontoTotal(float montoTotal) {
	this.montoTotal = montoTotal;
}

public int getNumeroCaja() {
	return numeroCaja;
}

public void setNumeroCaja(int numeroCaja) {
	this.numeroCaja = numeroCaja;
}

public String getConceptoIngreso() {
	return conceptoIngreso;
}

public void setConceptoIngreso(String conceptoIngreso) {
	this.conceptoIngreso = conceptoIngreso;
}

public String getUsuario() {
	return usuario;
}

public void setUsuario(String usuario) {
	this.usuario = usuario;
}

private static final long serialVersionUID = 1L;
  
}
