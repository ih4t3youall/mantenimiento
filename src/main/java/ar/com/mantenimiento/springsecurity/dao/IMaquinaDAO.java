package ar.com.mantenimiento.springsecurity.dao;

import java.util.List;

import ar.com.mantenimiento.entity.Maquina;

public interface IMaquinaDAO {

	
	Maquina findMaquinaById(int idMaquina);

	List<Maquina> findMaquinasByProyecto(int idProyecto);
	
}
