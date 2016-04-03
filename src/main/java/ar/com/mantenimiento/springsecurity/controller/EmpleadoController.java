package ar.com.mantenimiento.springsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.dto.CrearEmpleadoDTO;
import ar.com.mantenimiento.dto.ProyectoDTO;
import ar.com.mantenimiento.entity.AsociacionEmpleadoProyecto;
import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.Proyecto;
import ar.com.mantenimiento.entity.UsuarioAsignado;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.ProyectoDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.ProyectoHasUsuarioAsignadoDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.UserDao;
import ar.com.mantenimiento.springsecurity.dao.impl.UserProfileDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.UsuarioAsignadoDAO;
import ar.com.mantenimiento.springsecurity.model.User;
import ar.com.mantenimiento.utility.GsonUtility;

@Controller
@Transactional
public class EmpleadoController {

	@Autowired
	private ProyectoDAO proyectoDAO;
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private UserDao userDAO;
	
	@Autowired
	private UserProfileDAO userProfileDAO;
	
	@Autowired 
	private GsonUtility gsonUtility;
	
	@Autowired
	private UsuarioAsignadoDAO usuarioAsignadoDAO;
	
	@Autowired
	private Mapper dozerMapper;
	
	@Autowired
	private ProyectoHasUsuarioAsignadoDAO ProyectoHasUsuarioAsignadoDAO;
	
	
	
	
	
	
	
	@RequestMapping("admin/agregarEmpleado.htm")
	public ModelAndView agregarEmpleado(CrearEmpleadoDTO empleadoDTO) {

		
	
		
		
		userDAO.crearUsuario(empleadoDTO);
		
		
		
		ModelAndView mav = new ModelAndView("admin/exito/empleadoCreadoExito");
		// TODO base de datos

		return mav;

	}

	@RequestMapping("admin/formCrearEmpleado.htm")
	public ModelAndView formCrearEmpleado() {

		ModelAndView mav = new ModelAndView("admin/formularios/formularioCrearEmpleado");
		mav.addObject("empleadoDTO", new CrearEmpleadoDTO());
		
		List<String> types = new ArrayList<String>();
		types.add("USER");
		types.add("DBA");
		types.add("OPERARIO");
		types.add("ADMIN");
		mav.addObject("types",types);
		
		
		return mav;

	}
	
	
	@RequestMapping("admin/formAsignarEmpleado.htm")
	public ModelAndView formAsignarEmpleado(){
		
		ModelAndView mav = new ModelAndView("admin/formularios/formularioAsignarEmpleado");
		
		List<Proyecto> proyectos = proyectoDAO.findAllProyectos();
		
		 List<User> userEmpleado = userDAO.findAllEmpleados();
		
		
		mav.addObject("empleados",userEmpleado);
		List<Empresa> empresas = empresaDAO.findAllEmpresas();
		
		mav.addObject("empresas",empresas);
		mav.addObject("asociacionEmpleadoProyecto",new AsociacionEmpleadoProyecto());
		
		return mav;
		
	}

	
	@RequestMapping("admin/asignarEmpleado.htm")
	public ModelAndView asignarEmpleado(AsociacionEmpleadoProyecto asociacion){
		
		ModelAndView mav = new ModelAndView("admin/exito/asociacionExitosa");
		
		
		Proyecto proyecto = proyectoDAO.findProyectoByProyectId(asociacion.getIdProyecto());
		User findBySSO = userDAO.findBySSO(asociacion.getNombreEmpleado());
		
		UsuarioAsignado usAsig = new UsuarioAsignado();
		
		
		
		usAsig.addProyecto(proyecto);
		usAsig.setSsoId(asociacion.getNombreEmpleado());
		
		if(ProyectoHasUsuarioAsignadoDAO.existeAsignacion(findBySSO.getId(),proyecto.getId())){
			mav.setViewName("error/errorGenerico");
			mav.addObject("mensaje","La asignacion ya existe");
			mav.addObject("url","adminIni.htm");
			return mav;
		
		}else{
			usuarioAsignadoDAO.persist(usAsig);	
		
			
		}
		
		return mav;
		
	}
	
	
	@RequestMapping("admin/formDesAsignarEmpleado.htm")
	public ModelAndView formDesAsignarEmpleado(){
		
		ModelAndView mav = new ModelAndView("admin/formularios/formularioDesAsignarEmpleado");
		
		List<User> usersOperario = userDAO.findAllEmpleados();
		
		
		mav.addObject("empleados",usersOperario);
		
		mav.addObject("asociacionEmpleadoProyecto", new AsociacionEmpleadoProyecto());
		
		return mav;
		
	}
	
	
	
	
	@RequestMapping("admin/obtenerProyectosPorEmpleado.htm")
	public  @ResponseBody String obtenerProyectosPorEmpleado(String  ssoid){
		
		ModelAndView mav = new ModelAndView("admin/formularios/formularioDesAsignarEmpleado");
		
		 
		 List<Proyecto> proyectos = usuarioAsignadoDAO.findAssignamentsFromUser(ssoid);
		 
		 List<ProyectoDTO> proyectosDTO = new ArrayList<ProyectoDTO>();
		 
		 for (Proyecto proyecto : proyectos) {
			proyectosDTO.add(dozerMapper.map(proyecto, ProyectoDTO.class));
		}
		 
		 
		 
		 String respuesta = gsonUtility.getGson().toJson(proyectosDTO);
		
		return respuesta;
		
	}
	
	
	
}
