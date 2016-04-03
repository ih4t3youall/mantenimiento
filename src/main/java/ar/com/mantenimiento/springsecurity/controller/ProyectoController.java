package ar.com.mantenimiento.springsecurity.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.dto.CrearProyectoDTO;
import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.Proyecto;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.ProyectoDAO;

@Controller
@Transactional
public class ProyectoController {

	
	
	@Autowired
	private ProyectoDAO proyectoDAO;
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@RequestMapping("admin/formCrearProyecto.htm")
	public ModelAndView formCrearProyecto(){
		ModelAndView mav = new ModelAndView("admin/formularios/formCrearProyecto");
		Proyecto proyecto = new Proyecto();
		proyecto.setEmpresa(new Empresa());
		List<Empresa> empresas = empresaDAO.getAll(Empresa.class);

		mav.addObject("crearProyectoDTO",new CrearProyectoDTO());
		mav.addObject("empresas",empresas);
		
		return mav;
	}
	
	
	@RequestMapping("admin/crearProyecto.htm")
	public ModelAndView crearProyecto(CrearProyectoDTO crearProyectoDTO){
		ModelAndView mav = new ModelAndView("admin/exito/exito");
		mav.addObject("mensaje","proyecto creado con exito");
		
		Empresa empresa = empresaDAO.finEmpresaById(crearProyectoDTO.getEmpresaId());
		Proyecto proyecto = new Proyecto();
		proyecto.setEmpresa(empresa);
		proyecto.setNombre(crearProyectoDTO.getNombreProyecto());
		proyecto.setDescripcion(crearProyectoDTO.getDescripcionProyecto());
		
		proyectoDAO.persist(proyecto);
		
		return mav;
	}
	
}
