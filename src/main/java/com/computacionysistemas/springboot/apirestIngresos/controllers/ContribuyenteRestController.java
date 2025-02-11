package com.computacionysistemas.springboot.apirestIngresos.controllers;

import java.util.List;
import java.util.HashMap;
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

import com.computacionysistemas.springboot.apirestIngresos.models.entity.Contribuyente;
import com.computacionysistemas.springboot.apirestIngresos.models.services.IContribuyenteService;


import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ContribuyenteRestController {
	
	@Autowired
	private IContribuyenteService contribuyenteService;
	
	@GetMapping("/listar/contribuyentes")
	public List<Contribuyente> listadoContribuyentes(){
		return contribuyenteService.findAll();
	}
	
	
	
	
	@PostMapping("/crear/contribuyente")
	public ResponseEntity<?>  crearContribuyente(@Valid @RequestBody Contribuyente contribuyente ,  BindingResult resultadosDeValidacion){
		Contribuyente  nuevoContribuyente  = null;
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
			nuevoContribuyente = contribuyenteService.guardarContribuyente(contribuyente);	
		}catch ( DataAccessException ex ){
			response.put("mensaje","  Error  al  insertar nuevo Contribuyente en la Base  de  Datos ");
        	response.put("errores",ex.getMessage().concat(":").concat(ex.getMostSpecificCause().getMessage()));
        	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","  nuevo Contribuyente ha sido  Creado  con Exito !!! ");
    	response.put("contribuyente",nuevoContribuyente);
		return new  ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	};
	
	@PutMapping("/actualizar/contribuyente/{id}")
	public ResponseEntity<?> actualizarContribuyente(@RequestBody Contribuyente contribuyente,@PathVariable Long id ){
		Map<String,Object> response =  new  HashMap<String,Object>();
		Contribuyente contribuyenteActualizado = null;
		
	    Contribuyente contribuyenteForUpdated = contribuyenteService.buscarPorId(id);
	    if (contribuyenteForUpdated==null)
	    {
	    	response.put("mensaje", "Contribuyente ".concat(id.toString()).concat("no existe en la Base  de datos ") );
	    	return new  ResponseEntity<Map<String,Object>> (response, HttpStatus.NOT_FOUND);  
	    }
	    try {
	    	contribuyenteForUpdated.setNombre(contribuyente.getNombre());
	    	contribuyenteForUpdated.setApellido(contribuyente.getApellido());
	    	contribuyenteForUpdated.setEmail(contribuyente.getEmail());
	    	contribuyenteActualizado=contribuyenteService.guardarContribuyente(contribuyenteForUpdated);
	    }catch(DataAccessException e) {
	    	response.put("mensaje","  Error  al  Actualizar el Ccontribuyente en la Base  de  Datos ");
        	response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    response.put("mensaje","El contribuyente  Id  : (".concat(id.toString()).concat(") Fue Actualizado  con exito "));
	    response.put("contribuyente",contribuyenteActualizado);
		return new ResponseEntity<Map<String,Object>> (response,HttpStatus.ACCEPTED);  
	};
	
	
	
	@DeleteMapping("/eliminar/contribuyente/{id}")
	public  ResponseEntity<?> delete(@PathVariable Long id ) {
	
		Map<String,Object> response =  new  HashMap<String,Object>();
		Contribuyente contribuyenteForDelete = contribuyenteService.buscarPorId(id);
	    if (contribuyenteForDelete==null)
	    {
	     response.put("mensaje", "Contribuyente ".concat(id.toString()).concat(" Contribuyente no existe en la Base  de datos ") );
	     return new  ResponseEntity<Map<String,Object>> (response, HttpStatus.NOT_FOUND);  
	    }
	    
		try {
			contribuyenteService.eliminarContribuyente(id);	
		}catch(DataAccessException e ){
	    	response.put("mensaje","  Error  al  Eliminar el Cliente en la Base  de  Datos ");
        	response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","   Contribuyente Ha  sido  Eliminado  con Exito !!!  ");
		return  new ResponseEntity<Map<String,Object>> (response,HttpStatus.ACCEPTED); 
	}
	
	
}


	
	
	


