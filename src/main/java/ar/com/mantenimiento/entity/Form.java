package ar.com.mantenimiento.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the form database table.
 * 
 */
@Entity
@NamedQuery(name="Form.findAll", query="SELECT f FROM Form f")
public class Form implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idForm;

	private String conclusion;

	private String equipo;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_programada")
	private Date fechaProgramada;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_realizacion")
	private Date fechaRealizacion;

	@Column(name="nro_interno")
	private int nroInterno;

	@Column(name="nro_orden")
	private int nroOrden;

	private String observaciones;

	//bi-directional many-to-one association to Maquina
	@ManyToOne
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private Maquina maquina;

	
	@OneToMany
	private List<FormHasEpp> formHasEpps;

	//bi-directional many-to-one association to FormItem
	@OneToMany(mappedBy="form")
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private List<FormItem> formItems;

	public Form() {
	}

	public int getId() {
		return this.idForm;
	}

	public void setId(int id) {
		this.idForm = id;
	}

	public String getConclusion() {
		return this.conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getEquipo() {
		return this.equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public Date getFechaProgramada() {
		return this.fechaProgramada;
	}

	public void setFechaProgramada(Date fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

	public Date getFechaRealizacion() {
		return this.fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public int getNroInterno() {
		return this.nroInterno;
	}

	public void setNroInterno(int nroInterno) {
		this.nroInterno = nroInterno;
	}

	public int getNroOrden() {
		return this.nroOrden;
	}

	public void setNroOrden(int nroOrden) {
		this.nroOrden = nroOrden;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Maquina getMaquina() {
		return this.maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public List<FormHasEpp> getFormHasEpps() {
		return this.formHasEpps;
	}

	public void setFormHasEpps(List<FormHasEpp> formHasEpps) {
		this.formHasEpps = formHasEpps;
	}


	public List<FormItem> getFormItems() {
		return this.formItems;
	}

	public void setFormItems(List<FormItem> formItems) {
		this.formItems = formItems;
	}

	public FormItem addFormItem(FormItem formItem) {
		getFormItems().add(formItem);
		formItem.setForm(this);

		return formItem;
	}

	public FormItem removeFormItem(FormItem formItem) {
		getFormItems().remove(formItem);
		formItem.setForm(null);

		return formItem;
	}

}