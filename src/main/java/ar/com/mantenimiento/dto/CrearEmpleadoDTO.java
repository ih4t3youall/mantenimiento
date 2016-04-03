package ar.com.mantenimiento.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.com.mantenimiento.springsecurity.model.State;
import ar.com.mantenimiento.springsecurity.model.UserProfileType;

public class CrearEmpleadoDTO {


	//user
	
	private int id;

	private String ssoId;
	
	private String password;
		
	private String firstName;

	private String lastName;

	private String email;

	private String state=State.ACTIVE.getState();

	//profile
	private int profileId;	

	private String type = UserProfileType.USER.getUserProfileType();
	
	
	
	
	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	
	
	
	
	
}
