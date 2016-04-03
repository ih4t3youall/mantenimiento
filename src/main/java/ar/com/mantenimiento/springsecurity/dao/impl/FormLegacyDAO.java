package ar.com.mantenimiento.springsecurity.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.entity.FormLegacy;
import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IFormLegacyDAO;


@Repository("formLegacyDAO")
public class FormLegacyDAO extends AbstractDao<Integer, FormLegacy> implements IFormLegacyDAO{

	
	@Override
	@SuppressWarnings("unchecked")
	public List<FormLegacy> obtenerPorEmpresa(Integer empresa_id) {
		
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idEmpresa", empresa_id));
		List<FormLegacy> result =(List<FormLegacy>) crit.list();
		return result;
		
	}

	
	
	
}
