package ar.com.mantenimiento.springsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.dto.MaquinaDTO;
import ar.com.mantenimiento.dto.ProyectoDTO;
import ar.com.mantenimiento.entity.Maquina;
import ar.com.mantenimiento.entity.Proyecto;
import ar.com.mantenimiento.springsecurity.dao.impl.MaquinaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.ProyectoDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.UsuarioAsignadoDAO;
import ar.com.mantenimiento.utility.GsonUtility;

@Controller
@Transactional
public class AjaxController {

	@Autowired
	private ProyectoDAO proyectoDAO;
	@Autowired
	private MaquinaDAO maquinasDAO;
	@Autowired
	private GsonUtility gsonUtility;
	@Autowired
	private UsuarioAsignadoDAO usuarioAsignadoDAO;

	@Autowired
	private Mapper dozerMapper;

	//solo para spring form
	@RequestMapping("admin/ajaxProyectosEmpresa.htm")
	public @ResponseBody String ajaxProyectosEmpresa(int idEmpresa) {

		ModelAndView mav = new ModelAndView("admin/formularios/templateFormulario");
		// fin proyectos pro empresa
		List<Proyecto> proyectos = proyectoDAO.findProyectosByEmpresaId(idEmpresa);
		List<ProyectoDTO> proyectosDTO = new ArrayList<ProyectoDTO>();
		for (Proyecto proyecto : proyectos) {

			ProyectoDTO proyectoDTO = dozerMapper.map(proyecto, ProyectoDTO.class);
			proyectosDTO.add(proyectoDTO);

		}

		
		String respuesta = gsonUtility.getGson().toJson(proyectosDTO);
		return respuesta;

	}

	@RequestMapping("admin/ajaxMaquinasProyectos.htm")
	public @ResponseBody String ajaxMaquinasProyectos(int idProyecto) {

		// fin proyectos pro empresa
		Proyecto proyecto = proyectoDAO.findProyectoByProyectId(idProyecto);

		List<MaquinaDTO> maquinas = new ArrayList<MaquinaDTO>();
		for (Maquina maquina : proyecto.getMaquinas()) {
			
			MaquinaDTO maquinaDTO = dozerMapper.map(maquina, MaquinaDTO.class);
			
			maquinas.add(maquinaDTO);
			
			
		}

		String respuesta = gsonUtility.getGson().toJson(maquinas);

		return respuesta;

	}
	
	
	

}
