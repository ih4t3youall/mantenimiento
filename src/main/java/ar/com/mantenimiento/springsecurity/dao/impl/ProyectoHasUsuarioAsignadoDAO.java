package ar.com.mantenimiento.springsecurity.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.entity.ProyectoHasUsuarioAsignado;
import ar.com.mantenimiento.entity.UsuarioAsignado;
import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IProyectoHasUsuarioAsignadoDAO;

@Repository("ProyectoHasUsuarioAsignadoDAO")
public class ProyectoHasUsuarioAsignadoDAO  extends AbstractDao<Integer, ProyectoHasUsuarioAsignado>implements IProyectoHasUsuarioAsignadoDAO {

	@Override
	public boolean existeAsignacion(UsuarioAsignado usAsig) {
		
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("proyectoId", usAsig.getId()));
		crit.add(Restrictions.eq("usuarioAsignadoId", usAsig.getId()));
		
		
		
		return false;
	}

	@Override
	public boolean existeAsignacion(int userId, int proyectID) {
		
		
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("proyectoId", proyectID));
		crit.add(Restrictions.eq("usuarioAsignadoId", userId));
		Object uniqueResult = crit.uniqueResult();
		
		if(uniqueResult == null){
			
			return false;
			
		}else {
			
			
			return true;
		}
		
		
	}
	
}
