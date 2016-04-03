package ar.com.mantenimiento.springsecurity.dao;

import java.util.List;

import ar.com.mantenimiento.entity.FormLegacy;

public interface IFormLegacyDAO {

	List<FormLegacy> obtenerPorEmpresa(Integer empresa_id);

}
