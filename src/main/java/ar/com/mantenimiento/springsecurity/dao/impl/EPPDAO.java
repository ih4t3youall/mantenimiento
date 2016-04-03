package ar.com.mantenimiento.springsecurity.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.dto.EPPDTO;
import ar.com.mantenimiento.entity.Epp;
import ar.com.mantenimiento.entity.FormHasEpp;
import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IEPPDAO;

@Repository("eppDAO")
public class EPPDAO extends AbstractDao<Integer, Epp> implements IEPPDAO{

	
	@Autowired
	private FormHasEPPDAO formHasEPPDAO;
	
	@Autowired
	private Mapper dozerMapper;
	
	@Override
	public void guardarEPP(List<FormHasEpp> formHasEpps) {
		
		
		formHasEPPDAO.removeByIdForm(formHasEpps.get(0).getIdForm());
		
		for (FormHasEpp formHasEpp : formHasEpps) {
			
			formHasEPPDAO.persist(formHasEpp);
			
		}
		
		
		
	}
	
	@Override
	public List<EPPDTO> findEppByFormId(int formId){
		
		List<FormHasEpp> finFormHasEPPByIdForm = formHasEPPDAO.findFormHasEPPByIdForm(formId);
		List<EPPDTO> epps  = new ArrayList<EPPDTO>();
		
		for (FormHasEpp formHasEpp : finFormHasEPPByIdForm) {
		
			Criteria crit = createEntityCriteria();
			crit.add(Restrictions.eq("idEpp", formHasEpp.getIdEpp()));
			Epp epp = (Epp) crit.uniqueResult();
			
			if(epp != null){
			EPPDTO map = dozerMapper.map(epp, EPPDTO.class);
			map.setObligatorio((1==formHasEpp.getObligatorio()));
			epps.add(map);
			}
			
		}
		
		return epps;
	}


}
