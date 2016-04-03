package ar.com.mantenimiento.springsecurity.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.entity.Form;
import ar.com.mantenimiento.entity.FormItem;
import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IFormDAO;

@Repository("formDAO")
public class FormDAO extends AbstractDao<Integer, Form>implements IFormDAO {

	@Autowired
	private FormItemDAO formItemDAO;

	public Form findFormByMaquinaId(int maquinaId) {

		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("maquina.id", maquinaId));
		Form form = (Form) crit.uniqueResult();
		return form;

	}

	@Override
	public void removeItems(Form form) {

		for (FormItem formItem : form.getFormItems()) {
			formItemDAO.delete(formItem);
		}

	}

}
