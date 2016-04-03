package ar.com.mantenimiento.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the proyecto_has_usuario_asignado database table.
 * 
 */
@Embeddable 
@Table(name="proyecto_has_usuario_asignado")
@NamedQuery(name="ProyectoHasUsuarioAsignado.findAll", query="SELECT p FROM ProyectoHasUsuarioAsignado p")
public class ProyectoHasUsuarioAsignado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="proyecto_id")
	private int proyectoId;

	@Column(name="usuario_asignado_id")
	private int usuarioAsignadoId;

	public ProyectoHasUsuarioAsignado() {
	}

	public int getProyectoId() {
		return this.proyectoId;
	}

	public void setProyectoId(int proyectoId) {
		this.proyectoId = proyectoId;
	}

	public int getUsuarioAsignadoId() {
		return this.usuarioAsignadoId;
	}

	public void setUsuarioAsignadoId(int usuarioAsignadoId) {
		this.usuarioAsignadoId = usuarioAsignadoId;
	}

}