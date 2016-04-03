package ar.com.mantenimiento.springsecurity.dao.impl;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.entity.FormItem;
import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IFormItemDAO;

@Repository("formItemDAO")
@Transactional 
public class FormItemDAO extends AbstractDao<Integer, FormItem>implements IFormItemDAO  {
	
	
	
	

}
