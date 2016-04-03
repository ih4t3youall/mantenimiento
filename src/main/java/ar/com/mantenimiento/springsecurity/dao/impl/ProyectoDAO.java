package ar.com.mantenimiento.springsecurity.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.Proyecto;
import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IProyectosDAO;
import ar.com.mantenimiento.springsecurity.model.User;

@Repository("proyectosDAO")
public class ProyectoDAO extends AbstractDao<Integer, Proyecto>implements IProyectosDAO {

	@Override
	public List<Proyecto> findAllProyectos() {

		List<Proyecto> proyectos = getAll(Proyecto.class);

		return proyectos;
	}

	@Override
	public Proyecto findProyectoByProyectId(int proyectoId) {

		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", proyectoId));
		Object uniqueResult = crit.uniqueResult();
		return (Proyecto) uniqueResult;

	}

	@Override
	public List<Proyecto> findProyectosByEmpresaId(int idEmpresa) {

		Criteria crit = createEntityCriteria();
		Empresa empresa = new Empresa();
		empresa.setId(idEmpresa);

		crit.add(Restrictions.eq("empresa.id", idEmpresa));
		List list = (List<Proyecto>) crit.list();

		return list;

	}
	
	

}
