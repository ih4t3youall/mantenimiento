package ar.com.mantenimiento.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "form_item_legacy")
@NamedQuery(name = "FormItemLegacy.findAll", query = "SELECT f FROM FormItemLegacy f")
public class FormItemLegacy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idform_item_legacy")
	private int formItemLegacyId;
	private String label;
	private String posee;
	private String estado;
	private String observaciones;
	
	@ManyToOne
	private FormLegacy formLegacy;


	public int getFormItemLegacyId() {
		return formItemLegacyId;
	}

	public void setFormItemLegacyId(int formItemLegacyId) {
		this.formItemLegacyId = formItemLegacyId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPosee() {
		return posee;
	}

	public void setPosee(String posee) {
		this.posee = posee;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
