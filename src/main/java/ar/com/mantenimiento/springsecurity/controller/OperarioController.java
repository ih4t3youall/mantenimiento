package ar.com.mantenimiento.springsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.dto.IdMaquinaDTO;
import ar.com.mantenimiento.dto.MaquinaProyectoIdDTO;
import ar.com.mantenimiento.entity.Maquina;
import ar.com.mantenimiento.entity.Proyecto;
import ar.com.mantenimiento.springsecurity.dao.impl.MaquinaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.ProyectoDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.UsuarioAsignadoDAO;

@Controller
@Transactional
public class OperarioController {

	@Autowired
	private ProyectoDAO proyectoDAO;

	@Autowired
	private UsuarioAsignadoDAO usuarioAsignadoDAO;
	
	@Autowired
	private MaquinaDAO maquinaDAO;

	@RequestMapping("operario/inicio.htm")
	public ModelAndView inicioOperario() {

		ModelAndView mav = new ModelAndView("operario/inicio");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nombre = auth.getName();

		List<Proyecto> proyectosByUser = usuarioAsignadoDAO.findAssignamentsFromUser(nombre);

		mav.addObject("proyectos", proyectosByUser);

		return mav;

	}

	@RequestMapping("operario/proyectos.htm")
	public ModelAndView proyectos(int idProyecto) {

		// FIXME este metodo no anda
//		Proyecto proyecto = proyectoDAO.findProyectoByProyectId(idProyecto);
//
//		List<Maquina> maquinas = new ArrayList<Maquina>();
//
//		for (Maquina maquina : proyecto.getMaquinas()) {
//
//			maquinas.add(maquina);
//
//		}
		List<Maquina> maquinas = getMaquinas(idProyecto);
		

		ModelAndView mav = new ModelAndView("operario/proyectos");
		mav.addObject("maquinaProyectoIdDTO",new MaquinaProyectoIdDTO());
		mav.addObject("maquinas", maquinas);
		mav.addObject("idProyecto",idProyecto);
		

		return mav;

	}
	
	
	private List<Maquina> getMaquinas(int idProyecto){
		Proyecto proyecto = proyectoDAO.findProyectoByProyectId(idProyecto);

		List<Maquina> maquinas = new ArrayList<Maquina>();

		for (Maquina maquina : proyecto.getMaquinas()) {

			maquinas.add(maquina);

		}
		
		return maquinas;
		
		
	}
	
	
	@RequestMapping("operario/verSoloMaquinas.htm")
	public ModelAndView verSoloMAquinas(MaquinaProyectoIdDTO maquinaProyectoIdDTO){
		
		ModelAndView mav = new ModelAndView("operario/checklist/soloMaquinas");
		
		List<Maquina> maquinas = getMaquinas(maquinaProyectoIdDTO.getProyectoId());
		
		mav.addObject("maquinaProyectoIdDTO",maquinaProyectoIdDTO);
		mav.addObject("maquinas",maquinas);
		
		return mav;
		
		
		
		
	}
	
	

}
