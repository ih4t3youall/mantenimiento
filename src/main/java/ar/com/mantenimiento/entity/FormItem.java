package ar.com.mantenimiento.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the form_item database table.
 * 
 */
@Entity
@Table(name="form_item")
@NamedQuery(name="FormItem.findAll", query="SELECT f FROM FormItem f")
public class FormItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idform_item")
	private int idformItem;

	private String estado;

	private String label;

	private String observaciones;

	private String posee;

	//bi-directional many-to-one association to Form
	@ManyToOne
	private Form form;

	


	public FormItem() {
	}

	public int getIdformItem() {
		return this.idformItem;
	}

	public void setIdformItem(int idformItem) {
		this.idformItem = idformItem;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPosee() {
		return this.posee;
	}

	public void setPosee(String posee) {
		this.posee = posee;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

}