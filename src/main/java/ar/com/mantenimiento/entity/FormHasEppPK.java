package ar.com.mantenimiento.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the form_has_epp database table.
 * 
 */
@Embeddable
public class FormHasEppPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="form_id", insertable=false, updatable=false)
	private int formId;

	@Column(name="epp_id", insertable=false, updatable=false)
	private int eppId;

	public FormHasEppPK() {
	}
	public int getFormId() {
		return this.formId;
	}
	public void setFormId(int formId) {
		this.formId = formId;
	}
	public int getEppId() {
		return this.eppId;
	}
	public void setEppId(int eppId) {
		this.eppId = eppId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FormHasEppPK)) {
			return false;
		}
		FormHasEppPK castOther = (FormHasEppPK)other;
		return 
			(this.formId == castOther.formId)
			&& (this.eppId == castOther.eppId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.formId;
		hash = hash * prime + this.eppId;
		
		return hash;
	}
}