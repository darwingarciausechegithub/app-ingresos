package com.computacionysistemas.springboot.apirestIngresos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.computacionysistemas.springboot.apirestIngresos.models.entity.Formula;
import com.computacionysistemas.springboot.apirestIngresos.models.services.IFormulaService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FormulaRestController {
	
@Autowired
private IFormulaService formulaService; 

@GetMapping("/listar/formulas")
public List<Formula> listadoFormulas(){
	return formulaService.listarFormulas();
}

@PostMapping("/crear/formula")
public ResponseEntity<?>  crearFormual(@Valid @RequestBody Formula formula,  BindingResult resultadosDeValidacion){
	Formula  nuevaFormula  = null;
	Map<String,Object>  response = new  HashMap<String,Object>();
	
	if (resultadosDeValidacion.hasErrors())
	{
	List<String>  resultadosValidacion = 
    resultadosDeValidacion.getFieldErrors()
    			.stream()
    			.map(e ->{return "El campo '"+e.getField()+"'  "+e.getDefaultMessage();}
    			   ).collect(Collectors.toList());
        response.put("error", resultadosValidacion); 	
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
	}
	try { 
		nuevaFormula = formulaService.crearFormula(formula);	
	}catch ( DataAccessException ex ){
		response.put("mensaje","  Error  al  insertar nuevo Formula en la Base  de  Datos ");
    	response.put("errores",ex.getMessage().concat(":").concat(ex.getMostSpecificCause().getMessage()));
    	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	response.put("mensaje","  nuevo Contribuyente ha sido  Creado  con Exito !!! ");
	response.put("contribuyente",nuevaFormula);
	return new  ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
};

@PutMapping("/actualizar/formula/{id}")
public ResponseEntity<?> actualizarContribuyente(@Valid @RequestBody Formula formula,@PathVariable Long id , BindingResult resultadosDeValidacion ){
	
	Map<String,Object> response =  new  HashMap<String,Object>();
	
	if (resultadosDeValidacion.hasErrors())
	{
	List<String>  resultadosValidacion = 
    resultadosDeValidacion.getFieldErrors()
    			.stream()
    			.map(e ->{return "El campo '"+e.getField()+"'  "+e.getDefaultMessage();}
    			   ).collect(Collectors.toList());
        response.put("error", resultadosValidacion); 	
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
	}
	
	Formula formulaActualizada = null;
	
	Formula formulaForUpdated = formulaService.buscarPorIdFormula(id);
    
    if (formulaForUpdated==null)
    {
    	response.put("mensaje", "Formula".concat(id.toString()).concat("no existe en la Base  de datos ") );
    	return new  ResponseEntity<Map<String,Object>> (response, HttpStatus.NOT_FOUND);  
    }
    try {
    	formulaForUpdated.setIdFormula(formula.getIdFormula());
    	formulaForUpdated.setDescripcion(formula.getDescripcion());
    	formulaForUpdated.setFormula(formula.getFormula());
    	formulaActualizada=formulaService.actualizarFormula(formulaForUpdated);
    }catch(DataAccessException e) {
    	response.put("mensaje","  Error  al  Actualizar el Formula en la Base  de  Datos ");
    	response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
    	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    catch (Exception e) {
        response.put("mensaje", "Error inesperado");
        response.put("error", e.getCause().getMessage());
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }    
    response.put("mensaje","El contribuyente  Id  : (".concat(id.toString()).concat(") Fue Actualizado  con exito "));
    response.put("contribuyente",formulaActualizada);
	return new ResponseEntity<Map<String,Object>> (response,HttpStatus.ACCEPTED);  
};

@DeleteMapping("/eliminar/formula/{id}")
public  ResponseEntity<?> delete(@PathVariable Long id ) {

	Map<String,Object> response =  new  HashMap<String,Object>();
	Formula formulaForDelete = formulaService.buscarPorIdFormula(id);
    if (formulaForDelete==null)
    {
     response.put("mensaje", "Formula :".concat(id.toString()).concat("  no existe en la Base  de datos ") );
     return new  ResponseEntity<Map<String,Object>> (response, HttpStatus.NOT_FOUND);  
    }
    
	try {
		formulaService.eliminarFormula(id);	
	}catch(DataAccessException e ){
    	response.put("mensaje","  Error  al  Eliminar el formula en la Base  de  Datos ");
    	response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
    	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	response.put("mensaje","   Formula Ha  sido  Eliminado  con Exito !!!  ");
	return  new ResponseEntity<Map<String,Object>> (response,HttpStatus.ACCEPTED); 
}





}
