package com.computacionysistemas.springboot.apirestIngresos.models.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detingresos")
public class DetIngreso implements Serializable {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idTransaccion;  
	private int  mesDesde;
	private int  mesHasta;
	private int  anno;
	private float montoPago;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ingresoId")
	private Ingreso ingreso;
   
	@ManyToOne
    @JoinColumn(name = "conceptoId")
    private Concepto concepto; 
	

	public Concepto getConceptos() {
		return concepto;
	}

	public void setConceptos(Concepto conceptos) {
		this.concepto = conceptos;
	}

	
	public int getAnno() {
		return anno;
	}

	public void setAnno(int annoConc) {
		this.anno = annoConc;
	}

	public int getMesdesde() {
		return mesDesde;
	}

	public void setMesdesde(int mesDesde) {
		this.mesDesde = mesDesde;
	}

	public int getMeshasta() {
		return mesHasta;
	}

	public void setMeshasta(int meshasta) {
		this.mesHasta = meshasta;
	}
	public  Double calcularTotalConcepto() {
	     return  0.00;
	    		 //(double) (( this.meshasta - this.mesdesde )*conceptos.getTarfconc()) ; 	
	}

	public float getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(float montoPago) {
		this.montoPago = montoPago;
	}

	private static final long serialVersionUID = 1L;

}
