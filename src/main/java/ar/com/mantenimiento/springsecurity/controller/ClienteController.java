package ar.com.mantenimiento.springsecurity.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.entity.AsociacionClienteEmpresa;
import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.UserDao;
import ar.com.mantenimiento.springsecurity.model.User;

@Controller
@Transactional
public class ClienteController {

	@Autowired
	private EmpresaDAO empresaDAO;

	@Autowired
	private UserDao userDao;

	

	@RequestMapping("admin/formAsignarCliente.htm")
	public ModelAndView formAsignarCliente() {

		ModelAndView mav = new ModelAndView("cliente/formAsignarCliente");
		List<Empresa> empresas = empresaDAO.findAllEmpresas();

		List<User> clientes = userDao.findAllClientes();

		mav.addObject("clientes", clientes);
		mav.addObject("empresas", empresas);
		mav.addObject("asociacionClienteEmpresa",new AsociacionClienteEmpresa());
		
		return mav;

	}

	
	@RequestMapping("admin/asignarClienteEmpresa.htm")
	public ModelAndView asignarClienteEmpresa(AsociacionClienteEmpresa asoc){
		

		User user = userDao.getByKey(asoc.getIdCliente());
		user.setEmpresa_id(asoc.getIdEmpresa());
		userDao.persist(user);
		
		ModelAndView mav = new ModelAndView("admin/exito/exito");
		mav.addObject("mensaje","Asociacion creada con exito!");
		return mav;
		
		
	}
	
	
}
