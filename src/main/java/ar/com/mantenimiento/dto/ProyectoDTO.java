package ar.com.mantenimiento.dto;

import java.util.ArrayList;
import java.util.List;

public class ProyectoDTO {

	private int id;

	private String descripcion;

	private String nombre;

	private List<MaquinaDTO> maquinas;

	
	
	public void add(MaquinaDTO maquinaDTO){
		if(maquinas == null){
			
			maquinas = new ArrayList<MaquinaDTO>();
			
			
		}
		
		maquinas.add(maquinaDTO);
		
	}
	
	public List<MaquinaDTO> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(List<MaquinaDTO> maquinas) {
		this.maquinas = maquinas;
	}

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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
