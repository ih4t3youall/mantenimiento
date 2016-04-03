package ar.com.mantenimiento.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private String nombre;

	private String urlImagen;

	//bi-directional many-to-one association to Proyecto
	@OneToMany(mappedBy="empresa")
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private List<Proyecto> proyectos;

	public Empresa() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrlImagen() {
		return this.urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public List<Proyecto> getProyectos() {
		return this.proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Proyecto addProyecto(Proyecto proyecto) {
		getProyectos().add(proyecto);
		proyecto.setEmpresa(this);

		return proyecto;
	}

	public Proyecto removeProyecto(Proyecto proyecto) {
		getProyectos().remove(proyecto);
		proyecto.setEmpresa(null);

		return proyecto;
	}

}