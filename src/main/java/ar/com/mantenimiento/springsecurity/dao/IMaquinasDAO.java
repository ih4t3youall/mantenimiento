package ar.com.mantenimiento.springsecurity.dao;

import java.util.List;

import ar.com.mantenimiento.entity.Maquina;

public interface IMaquinasDAO {

	
	
	public List<Maquina> findMaquinasByProyecto(int idProyecto);
	
}
