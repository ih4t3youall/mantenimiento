package ar.com.mantenimiento.springsecurity.dao;

import java.util.List;

import ar.com.mantenimiento.dto.EPPDTO;
import ar.com.mantenimiento.entity.Epp;
import ar.com.mantenimiento.entity.FormHasEpp;

public interface IEPPDAO {


	void guardarEPP(List<FormHasEpp> formHasEpps);

	List<EPPDTO> findEppByFormId(int formId);

}
