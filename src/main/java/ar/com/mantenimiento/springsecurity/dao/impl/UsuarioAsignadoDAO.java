package ar.com.mantenimiento.springsecurity.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.entity.Proyecto;
import ar.com.mantenimiento.entity.UsuarioAsignado;
import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IUsuarioAsignadoDAO;

@Repository("usuarioAsignadoDAO")
public class UsuarioAsignadoDAO extends AbstractDao<Integer, UsuarioAsignado>  implements IUsuarioAsignadoDAO{

	
	@Override
	public List<Proyecto> findAssignamentsFromUser(String nombreEmpleado) {
		
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", nombreEmpleado));
		List<UsuarioAsignado> lista=  (List<UsuarioAsignado>)crit.list();	
		
		List<Proyecto> proyectos= new ArrayList<Proyecto>();
		
		for (UsuarioAsignado usuarioAsignado : lista) {
			
			
			for (Proyecto proyecto : usuarioAsignado.getProyectos()) {
			
				proyectos.add(proyecto);
				
			}
			
			
			
		}
		
		
		return proyectos;
		
		
		
	}
	
	
	@Override
	public UsuarioAsignado findUsuarioAsignado(String sso_id){
		
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso_id));
		UsuarioAsignado lista=  (UsuarioAsignado)crit.uniqueResult();	
		return lista;
		
	}


}
