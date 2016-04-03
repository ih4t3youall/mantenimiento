package ar.com.mantenimiento.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "form_legacy")
@NamedQuery(name = "FormLegacy.findAll", query = "SELECT f FROM FormLegacy f")
public class FormLegacy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFormLegacy;

	private int idEmpresa;
	private int idProyecto;
	private int idMaquina;
	private boolean aptoServicio;
	private String observaciones;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_programada")
	private Date fechaProgramada;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_realizacion")
	private Date fechaRealizacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "last_modify_date")
	private Date lastModifyDate;

	@OneToMany
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private List<FormItemLegacy> formItemsLegacy;

	public void addItem(FormItemLegacy item) {

		if (formItemsLegacy == null) {

			formItemsLegacy = new ArrayList<FormItemLegacy>();
		}
		formItemsLegacy.add(item);

	}

	
	
	
	public Date getFechaProgramada() {
		return fechaProgramada;
	}




	public void setFechaProgramada(Date fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}




	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}




	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}




	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public int getIdFormLegacy() {
		return idFormLegacy;
	}

	public void setIdFormLegacy(int idFormLegacy) {
		this.idFormLegacy = idFormLegacy;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public int getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(int idMaquina) {
		this.idMaquina = idMaquina;
	}

	public boolean isAptoServicio() {
		return aptoServicio;
	}

	public void setAptoServicio(boolean aptoServicio) {
		this.aptoServicio = aptoServicio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<FormItemLegacy> getFormItemsLegacy() {
		return formItemsLegacy;
	}

	public void setFormItemsLegacy(List<FormItemLegacy> formItemsLegacy) {
		this.formItemsLegacy = formItemsLegacy;
	}

}
