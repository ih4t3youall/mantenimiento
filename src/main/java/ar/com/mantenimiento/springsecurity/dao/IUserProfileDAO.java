package ar.com.mantenimiento.springsecurity.dao;

import java.util.List;

import ar.com.mantenimiento.springsecurity.model.UserProfile;

public interface IUserProfileDAO {

	
	List<UserProfile> findAllProfiles();
	
	
}
