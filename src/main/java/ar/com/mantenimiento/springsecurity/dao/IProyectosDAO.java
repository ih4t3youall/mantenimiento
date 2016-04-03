package ar.com.mantenimiento.springsecurity.dao;

import java.util.List;

import ar.com.mantenimiento.entity.Proyecto;

public interface IProyectosDAO {

	
	public List<Proyecto> findAllProyectos();
	public List<Proyecto> findProyectosByEmpresaId(int idEmpresa);
	public Proyecto findProyectoByProyectId(int proyectoId);
	
}
