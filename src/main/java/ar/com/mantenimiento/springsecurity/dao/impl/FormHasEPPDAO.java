package ar.com.mantenimiento.springsecurity.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.entity.FormHasEpp;
import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IFormHasEPPDAO;

@Repository("formHasEPPDAO")
public class FormHasEPPDAO extends AbstractDao<Integer, FormHasEpp> implements IFormHasEPPDAO {

	
	
	@Override
	public List<FormHasEpp> findFormHasEPPByIdForm(int formId){
		

		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idForm", formId));
		return (List<FormHasEpp>) crit.list();
		
		
	}
	
	
	@Override
	public void removeByIdForm(int idForm) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idForm", idForm));
		List<FormHasEpp>lista =  (List<FormHasEpp>)  crit.list();
		
		for (FormHasEpp formHasEpp : lista) {
			
			delete(formHasEpp);
			
		}
		
		
		
	}
}
