package ar.com.mantenimiento.springsecurity.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.Maquina;
import ar.com.mantenimiento.entity.Proyecto;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.MaquinaDAO;
import ar.com.mantenimiento.utility.GsonUtility;

@Controller
@Transactional
public class MaquinaController {

	@Autowired
	private MaquinaDAO maquinaDAO;
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private GsonUtility gsonUtility;
	
	@RequestMapping("admin/formCrearMaquina.htm")
	public ModelAndView formCrearMaquina() {

		ModelAndView mav = new ModelAndView("admin/formularios/formCrearMaquina");
		mav.addObject("maquinaDTO", new Maquina());

		return mav;

	}

	
	@RequestMapping("admin/formCrearMaquinaModal.htm")
	public ModelAndView formCrearMaquinaModal() {

		ModelAndView mav = new ModelAndView("admin/formularios/formCrearMaquinaModal");
		mav.addObject("maquinaDTO", new Maquina());

		return mav;

	}
	
	@RequestMapping("admin/insertarMaquinaModal.htm")
	public @ResponseBody String insertarMaquinaModal(String nombre,String descripcion, int idProyecto,int idEmpresa){
		Empresa empresa = empresaDAO.finEmpresaById(idEmpresa);
//		guardo la maquina para saber el id luego de ser insertada en la base de datos proque el parametro es por referencia
		Maquina aux = null;
		for (Proyecto proyecto : empresa.getProyectos()) {
			if (proyecto.getId() == idProyecto){
				
				Maquina maquina = new Maquina();
				maquina.setDescripcion(descripcion);
				maquina.setNombre(nombre);
				aux = maquina;
				proyecto.addMaquina(maquina);
				
			}
			
			
		}
		
		
		empresaDAO.persist(empresa);
	
		
			String maquinaJson = gsonUtility.getGson().toJson(aux);
		
		
		
		return maquinaJson ;
	}
	

	
	@RequestMapping("admin/crearMaquina.htm")
	public ModelAndView crearMaquina(Maquina maquina) {

		ModelAndView mav = new ModelAndView("admin/exito/crearMaquinaExito");
		maquinaDAO.persist(maquina);
		return mav;

	}
	
}
