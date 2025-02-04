package com.computacionysistemas.springboot.apirestIngresos.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computacionysistemas.springboot.apirestIngresos.models.dao.IContribuyenteDao;
import com.computacionysistemas.springboot.apirestIngresos.models.entity.Contribuyente;


@Service
public class ContribuyenteServiceImpl  implements IContribuyenteService {
	@Autowired
	IContribuyenteDao  contribuyenteDao;
	

	@Override
	public List<Contribuyente> listarContribuyentes() {
		return (List<Contribuyente>) contribuyenteDao.findAll();
	}

	@Override
	public Contribuyente guardarContribuyente(Contribuyente contribuyente) {
		return this.contribuyenteDao.save(contribuyente);
	}

	@Override
	public void eliminarContribuyente(Long id) {
		 this.contribuyenteDao.deleteById(id);
	}

	@Override
	public Contribuyente actulizarContribuyente(Contribuyente contribuyente) {
		return this.contribuyenteDao.save(contribuyente);
	}

	@Override
	public List<Contribuyente> buscarPorNombre(String nombre){
		return this.contribuyenteDao.findByNombreContainingIgnoreCase(nombre);
	}

	@Override
	public List<Contribuyente> buscarPorApellido(String apellido) {
		return this.contribuyenteDao.findByApellidoContainingIgnoreCase(apellido);
	}

	@Override
	public Contribuyente buscarPorCedula(String cedula) {
		return this.contribuyenteDao.findByCedula(cedula) ;
	}

	@Override
	public Contribuyente buscarPorId(Long id) {
        return this.contribuyenteDao.findById(id).orElse(null); 
		
	}
	@Override
	public Contribuyente buscarPorEmail(String email) {
		return this.contribuyenteDao.findByemail(email);
	}

	@Override
	public List<Contribuyente> findAll() {

		return (List<Contribuyente>) contribuyenteDao.findAll();
	}

	//@Override
	//public Contribuyente buscarPorEmail(String email) {
	
	//	return  
	//}


}
