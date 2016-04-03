package ar.com.mantenimiento.springsecurity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.com.mantenimiento.springsecurity.dao.AbstractDao;
import ar.com.mantenimiento.springsecurity.dao.IUserProfileDAO;
import ar.com.mantenimiento.springsecurity.model.UserProfile;
@Repository("userProfileDAO")
public class UserProfileDAO extends AbstractDao<Integer, UserProfile>implements IUserProfileDAO { 


@Override
public List<UserProfile> findAllProfiles() {
	
	
	List<UserProfile> userProfiles = getAll(UserProfile.class);
	
	return userProfiles;
}
}