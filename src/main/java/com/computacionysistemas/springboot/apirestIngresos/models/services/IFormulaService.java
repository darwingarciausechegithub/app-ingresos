package com.computacionysistemas.springboot.apirestIngresos.models.services;

import java.util.List;

import com.computacionysistemas.springboot.apirestIngresos.models.entity.Formula;

public interface IFormulaService  {
      public Formula  crearFormula(Formula formula);
      public Formula  actualizarFormula(Formula formula);
      public void  eliminarFormula(Long id);
      public List<Formula>  listarFormulas();
      public List<Formula>  buscarPorFormula(String formula);
      public List<Formula>  buscarPorDescripcion(String descripcion);
      public Formula  buscarPorIdFormula(Long id);
      
      
      
}
