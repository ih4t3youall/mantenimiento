package ar.com.mantenimiento.springsecurity.service;

import ar.com.mantenimiento.springsecurity.model.User;

public interface UserService  {

	User findById(int id);
	
	User findBySso(String sso);
	
}