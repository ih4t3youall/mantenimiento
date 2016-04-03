package ar.com.mantenimiento.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the usuario_asignado database table.
 * 
 */
@Entity
@Table(name="usuario_asignado")
@NamedQuery(name="UsuarioAsignado.findAll", query="SELECT u FROM UsuarioAsignado u")
public class UsuarioAsignado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="sso_id")
	private String ssoId;

	//bi-directional many-to-many association to Proyecto
	@ManyToMany
	@JoinTable(
		name="proyecto_has_usuario_asignado"
		, joinColumns={
			@JoinColumn(name="usuario_asignado_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="proyecto_id")
			}
		)
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private List<Proyecto> proyectos;

	public UsuarioAsignado() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSsoId() {
		return this.ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public List<Proyecto> getProyectos() {
		return this.proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public void addProyecto(Proyecto proyecto) {
		if(proyectos == null){
			proyectos = new ArrayList<Proyecto>();
		}
		proyectos.add(proyecto);
	}

}