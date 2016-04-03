package ar.com.mantenimiento.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * The persistent class for the form_has_epp database table.
 * 
 */
@Entity
@Table(name = "form_has_epp")
@NamedQuery(name = "FormHasEpp.findAll", query = "SELECT f FROM FormHasEpp f")
public class FormHasEpp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idForm;
	
	@Id
	private int idEpp;

	private int obligatorio;

	// bi-directional many-to-one association to Epp
	

	// bi-directional many-to-one association to Form

	public FormHasEpp() {
	}


	public int getIdForm() {
		return idForm;
	}


	public void setIdForm(int idForm) {
		this.idForm = idForm;
	}


	public int getIdEpp() {
		return idEpp;
	}


	public void setIdEpp(int idEpp) {
		this.idEpp = idEpp;
	}


	public int getObligatorio() {
		return this.obligatorio;
	}

	public void setObligatorio(int obligatorio) {
		this.obligatorio = obligatorio;
	}



}