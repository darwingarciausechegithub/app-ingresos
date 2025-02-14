package com.computacionysistemas.springboot.apirestIngresos.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computacionysistemas.springboot.apirestIngresos.models.dao.IFormulaDao;
import com.computacionysistemas.springboot.apirestIngresos.models.entity.Formula;
@Service
public class FormulaServiceImpl implements IFormulaService{
	@Autowired
	IFormulaDao  formulaDao;

	@Override
	public Formula crearFormula(Formula formula) {
		return this.formulaDao.save(formula) ;
	}

	@Override
	public Formula actualizarFormula(Formula formula) {
		return this.formulaDao.save(formula);
	}

	@Override
	public void  eliminarFormula(Long id) {
		this.formulaDao.deleteById(id);
	}

	@Override
	public List<Formula> listarFormulas() {
 		return (List<Formula>) this.formulaDao.findAll();
	}

	@Override
	public List<Formula> buscarPorFormula(String formula) {
		return this.formulaDao.findByFormulaContainingIgnoreCase(formula);
	}

	@Override
	public List<Formula> buscarPorDescripcion(String descripcion) {
		return this.formulaDao.findByDescripcionContainingIgnoreCase(descripcion);
	}

	@Override
	public Formula buscarPorIdFormula(Long id) {
		return this.formulaDao.findById(id).orElse(null);
	}

}
