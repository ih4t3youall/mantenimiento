package ar.com.mantenimiento.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.FormItemLegacy;
import ar.com.mantenimiento.entity.Maquina;
import ar.com.mantenimiento.entity.Proyecto;

public class FormLegacyDTO {

	private int idFormLegacy;

	private EmpresaDTO empresa;
	private ProyectoDTO proyecto;
	private MaquinaDTO maquina;
	private boolean aptoServicio;
	private String observaciones;

	private String fechaProgramada;

	private String fechaRealizacion;

	private Date lastModifyDate;

	private List<FormItemLegacy> formItemsLegacy;

	public void addItem(FormItemLegacy item) {

		if (formItemsLegacy == null) {

			formItemsLegacy = new ArrayList<FormItemLegacy>();
		}
		formItemsLegacy.add(item);

	}

	public String getFechaProgramada() {
		return fechaProgramada;
	}

	public void setFechaProgramada(String fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

	public String getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(String fechaRealizacion) {
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

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public ProyectoDTO getProyecto() {
		return proyecto;
	}

	public void setProyecto(ProyectoDTO proyecto) {
		this.proyecto = proyecto;
	}

	public MaquinaDTO getMaquina() {
		return maquina;
	}

	public void setMaquina(MaquinaDTO maquina) {
		this.maquina = maquina;
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
