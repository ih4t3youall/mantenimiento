package ar.com.mantenimiento.dto;

import java.util.List;

import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.FormHasEpp;
import ar.com.mantenimiento.entity.FormItem;
import ar.com.mantenimiento.entity.Maquina;
import ar.com.mantenimiento.entity.Proyecto;

public class FormDTO {
	private int id;

	private String conclusion;

	private String equipo;

	private String fechaProgramada;

	private String fechaRealizacion;

	private int nroInterno;

	private int nroOrden;

	private String observaciones;

	private Maquina maquina;

	private Boolean aptoServicio;

	private List<FormItem> formItems;

	private List<FormHasEpp> formHasEpps;

	private Proyecto proyecto;

	private Empresa empresa;
	
	private String noConformidad;
	
	

	public String getNoConformidad() {
		return noConformidad;
	}

	public void setNoConformidad(String noConformidad) {
		this.noConformidad = noConformidad;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getFechaProgramada() {
		return fechaProgramada;
	}

	public void setFechaProgramada(String fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

	public int getNroInterno() {
		return nroInterno;
	}

	public void setNroInterno(int nroInterno) {
		this.nroInterno = nroInterno;
	}

	public int getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(int nroOrden) {
		this.nroOrden = nroOrden;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public List<FormItem> getFormItems() {
		return formItems;
	}

	public void setFormItems(List<FormItem> formItems) {
		this.formItems = formItems;
	}

	public List<FormHasEpp> getFormHasEpps() {
		return formHasEpps;
	}

	public void setFormHasEpps(List<FormHasEpp> formHasEpps) {
		this.formHasEpps = formHasEpps;
	}

	public Boolean getAptoServicio() {
		return aptoServicio;
	}

	public void setAptoServicio(Boolean aptoServicio) {
		this.aptoServicio = aptoServicio;
	}

	public String getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(String fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

}
