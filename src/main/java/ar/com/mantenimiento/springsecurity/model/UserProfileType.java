package ar.com.mantenimiento.springsecurity.model;

public enum UserProfileType {
	USER("USER"),
	DBA("DBA"),
	OPERARIO("OPERARIO"),
	ADMIN("ADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
