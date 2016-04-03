package ar.com.mantenimiento.springsecurity.dao;

import java.util.List;

import ar.com.mantenimiento.entity.FormHasEpp;

public interface IFormHasEPPDAO {

	List<FormHasEpp> findFormHasEPPByIdForm(int formId);

	void removeByIdForm(int idForm);

}
