package ar.com.mantenimiento.springsecurity.dao;

import java.util.List;

import ar.com.mantenimiento.dto.CrearEmpleadoDTO;
import ar.com.mantenimiento.springsecurity.model.User;

public interface IUserDao {

	User findById(int id);
	
	User findBySSO(String sso);

	void crearUsuario(CrearEmpleadoDTO empleadoDTO);

	List<User> findAllEmpleados();

	List<User> findAllClientes();
	
}

