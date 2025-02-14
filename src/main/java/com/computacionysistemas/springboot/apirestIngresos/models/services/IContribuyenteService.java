package com.computacionysistemas.springboot.apirestIngresos.models.services;

import java.util.List;

import com.computacionysistemas.springboot.apirestIngresos.models.entity.Contribuyente;

public interface IContribuyenteService {
	
	   public List<Contribuyente> listarContribuyentes(); 
	   public Contribuyente guardarContribuyente(Contribuyente contribuyente);
	   public void eliminarContribuyente(Long id);
	   public Contribuyente actulizarContribuyente(Contribuyente contribuyente);
	   public List<Contribuyente> buscarPorNombre(String nombre);
	   public List<Contribuyente> buscarPorApellido(String apellido);
	   public Contribuyente buscarPorCedula(String  cedula);
	   public Contribuyente buscarPorId(Long id );
	   public Contribuyente buscarPorEmail(String email);
	   public List<Contribuyente> findAll();
}
