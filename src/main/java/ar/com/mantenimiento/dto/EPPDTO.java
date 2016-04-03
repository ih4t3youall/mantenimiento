package ar.com.mantenimiento.dto;

import java.util.List;

import ar.com.mantenimiento.entity.FormHasEpp;

public class EPPDTO {

	private int idEpp;

	private String descripcion;

	private String imagen;

	private String nombre;
	
	private boolean obligatorio;

	private List<FormHasEpp> formHasEpps;

	private byte[] byteImg;



	public int getIdEpp() {
		return idEpp;
	}

	public void setIdEpp(int idEpp) {
		this.idEpp = idEpp;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<FormHasEpp> getFormHasEpps() {
		return formHasEpps;
	}

	public void setFormHasEpps(List<FormHasEpp> formHasEpps) {
		this.formHasEpps = formHasEpps;
	}

	public byte[] getByteImg() {
		return byteImg;
	}

	public void setByteImg(byte[] byteImg) {
		this.byteImg = byteImg;
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}
	
	

}
