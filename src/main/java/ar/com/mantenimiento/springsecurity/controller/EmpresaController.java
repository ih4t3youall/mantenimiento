package ar.com.mantenimiento.springsecurity.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;

@Controller
@Transactional
public class EmpresaController {

	@Autowired
	private EmpresaDAO empresaDAO;
	
	
	@RequestMapping("admin/formCrearEmpresa.htm")
	public ModelAndView ajaxMaquinasProyectos() {

		ModelAndView mav = new ModelAndView("admin/formularios/formularioCrearEmpresa");
//		mav.addObject("empresaDTO",new Empresa());
		return mav;

	}
	
	
	@RequestMapping("admin/crearEmpresa.htm")
	public @ResponseBody  String ajaxMaquinasProyectos(Empresa empresa) {
 
//		ModelAndView mav = new ModelAndView("admin/exito/crearEmpresaExito");
		empresaDAO.persist(empresa);
		
		return String.valueOf(empresa.getId());

	}
	
	
}
