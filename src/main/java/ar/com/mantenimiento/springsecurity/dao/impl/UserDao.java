package ar.com.mantenimiento.springsecurity.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.Mapper;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.dto.CrearEmpleadoDTO;
import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IUserDao;
import ar.com.mantenimiento.springsecurity.model.User;
import ar.com.mantenimiento.springsecurity.model.UserProfile;

@Repository("userDAO")
public class UserDao extends AbstractDao<Integer, User> implements IUserDao {

	
	@Autowired
	private Mapper dozerMapper;
	
	@Autowired 
	private UserProfileDAO userProfileDAO;
	
	@Override
	public User findById(int id) {
		return getByKey(id);
	}
	@Override
	public User findBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		return (User) crit.uniqueResult();
	}
	@Override
	public void crearUsuario(CrearEmpleadoDTO empleadoDTO) {
		User user = dozerMapper.map(empleadoDTO, User.class);
		UserProfile userProfile = dozerMapper.map(empleadoDTO, UserProfile.class);
		
		List<UserProfile> userProfiles = userProfileDAO.findAllProfiles();
		
		for (UserProfile userProfile2 : userProfiles) {
			if(userProfile2.getType().equals(userProfile.getType().trim())){
				
				
				Set<UserProfile> aux = new HashSet<UserProfile>();
				aux.add(userProfile2);
				user.setUserProfiles(aux);
				
			}
			
			
		}
		
		persist(user);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllEmpleados() {

		Criteria crit = createEntityCriteria();
		return (List<User>) crit.createAlias("userProfiles", "up").add(Restrictions.eq("up.type", "OPERARIO")).list();
		
		
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllClientes() {

		Criteria crit = createEntityCriteria();
		return (List<User>) crit.createAlias("userProfiles", "up").add(Restrictions.eq("up.type", "USUARIO")).list();
		
		
		
		
	}
	
	

	
}
