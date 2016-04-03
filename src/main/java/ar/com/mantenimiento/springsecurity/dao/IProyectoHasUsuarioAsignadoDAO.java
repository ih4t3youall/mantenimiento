package ar.com.mantenimiento.springsecurity.dao;

import ar.com.mantenimiento.entity.UsuarioAsignado;

public interface IProyectoHasUsuarioAsignadoDAO {

	
	boolean existeAsignacion(UsuarioAsignado usAsig);

	boolean existeAsignacion(int userId, int proyectID);
	
}
