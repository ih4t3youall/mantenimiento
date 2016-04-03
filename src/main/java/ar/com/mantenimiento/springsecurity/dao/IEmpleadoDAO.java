package ar.com.mantenimiento.springsecurity.dao;

import java.util.List;

import ar.com.mantenimiento.dto.CrearEmpleadoDTO;
import ar.com.mantenimiento.entity.Empleado;

public interface IEmpleadoDAO {

	
	
	List<Empleado> findAllEmpleados();

	void crearEmpleado(CrearEmpleadoDTO empleadoDTO);
	
}
