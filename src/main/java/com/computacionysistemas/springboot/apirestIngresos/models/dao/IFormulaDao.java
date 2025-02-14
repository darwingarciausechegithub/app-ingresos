package com.computacionysistemas.springboot.apirestIngresos.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.computacionysistemas.springboot.apirestIngresos.models.entity.Formula;

public interface IFormulaDao extends CrudRepository<Formula,Long> {
	public List<Formula> findByDescripcionContainingIgnoreCase(String descripcion);
	public List<Formula> findByFormulaContainingIgnoreCase(String formula);
}
