package ar.com.mantenimiento.springsecurity.dao;

import java.util.List;

import ar.com.mantenimiento.entity.Empresa;

public interface IEmpresasDAO {

	
	List<Empresa> findAllEmpresas();
	
	Empresa finEmpresaById(int nombre);

	Empresa findEmpresaByProyectId(int idEmpresa);
	
	
}
