package ar.com.mantenimiento.dto;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDTO {

	private int id;

	private String descripcion;

	private String nombre;

	private String urlImagen;

	private List<ProyectoDTO> proyectos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void add(ProyectoDTO proyectoDTO) {

		if (proyectos == null) {

			proyectos = new ArrayList<ProyectoDTO>();

		}

		proyectos.add(proyectoDTO);

	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public List<ProyectoDTO> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<ProyectoDTO> proyectos) {
		this.proyectos = proyectos;
	}

}
