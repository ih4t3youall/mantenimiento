package ar.com.mantenimiento.springsecurity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.dto.CrearEmpleadoDTO;
import ar.com.mantenimiento.entity.Empleado;
import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IEmpleadoDAO;

@Repository("empleadoDAO")
public class EmpleadoDAO extends AbstractDao<Integer, Empleado>implements IEmpleadoDAO {

	@Override
	public List<Empleado> findAllEmpleados() {
		
		
		
		return getAll(Empleado.class);
		
	}
	
	@Override
	public void crearEmpleado(CrearEmpleadoDTO empleadoDTO){
		
		
		
	}
	
	
	
	

}