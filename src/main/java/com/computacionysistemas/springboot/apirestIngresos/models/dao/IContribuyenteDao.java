package com.computacionysistemas.springboot.apirestIngresos.models.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.computacionysistemas.springboot.apirestIngresos.models.entity.Contribuyente;

public interface IContribuyenteDao extends CrudRepository<Contribuyente, Long> {
	public List<Contribuyente> findByNombreContainingIgnoreCase(String nombre);
	public List<Contribuyente> findByApellidoContainingIgnoreCase(String apellido);
	public Contribuyente findByemail(String email);
	public Contribuyente findByCedula(String cedula);
	public List<Contribuyente> findAll();
	public void deleteById(Long id);
	
}
