package ar.com.mantenimiento.springsecurity.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.dto.EmpresaDTO;
import ar.com.mantenimiento.dto.LegacyDTO;
import ar.com.mantenimiento.dto.MaquinaDTO;
import ar.com.mantenimiento.dto.ProyectoDTO;
import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.FormLegacy;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.FormLegacyDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.MaquinaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.ProyectoDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.UserDao;
import ar.com.mantenimiento.springsecurity.model.User;
import ar.com.mantenimiento.utility.ImageConverterUtility;

@Controller
@Transactional
public class UsuarioController {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private ProyectoDAO proyectoDAO;
	@Autowired
	private MaquinaDAO maquinaDAO;

	@Autowired
	private FormLegacyDAO formLegacyDAO;

	@Autowired
	private Mapper dozerMapper;

	private User user;

	@RequestMapping("user/userIni.htm")
	public ModelAndView inicioUsuario(Principal principal) {

		ModelAndView mav = new ModelAndView("user/userIni");

		String username = principal.getName();

		User user = userDao.findBySSO(username);
		this.user = user;

		List<Empresa> empresas = empresaDAO.findAllEmpresas();
		
		
		List<EmpresaDTO> columnaA = new ArrayList<EmpresaDTO>();
		List<EmpresaDTO> columnaB = new ArrayList<EmpresaDTO>();

		boolean flag = true;

		for (Empresa empresa : empresas) {

			EmpresaDTO empresaDTO = dozerMapper.map(empresa, EmpresaDTO.class);
			
			empresaDTO.setUrlImagen(ImageConverterUtility.convertImage(empresa.getUrlImagen()));
			
			if (flag) {

				columnaA.add(empresaDTO);
				flag = false;

			} else {

				columnaB.add(empresaDTO);
				flag = true;

			}

		}

		mav.addObject("empresa", new Empresa());
		mav.addObject("columnaA", columnaA);
		mav.addObject("columnaB", columnaB);
		mav.addObject("empresa_id", user.getEmpresa_id());

		return mav;

	}

	@RequestMapping("user/miEmpresa.htm")
	public ModelAndView empresaSeleccionada(Empresa empresa1) {
		ModelAndView mav = new ModelAndView();
		Empresa empresa = empresaDAO.finEmpresaById(empresa1.getId());
		
		
		
		// controla del lado del servidor que tenga acceso ya que el javascript
		// es hackeable

		List<FormLegacy> formLegacys = formLegacyDAO.obtenerPorEmpresa(user.getEmpresa_id());
		
		
		List<LegacyDTO> legacyDTO = new ArrayList<LegacyDTO>(); 
		EmpresaDTO empresaDTO = dozerMapper.map(empresa, EmpresaDTO.class);
		
		for (FormLegacy formLegacy : formLegacys) {
			LegacyDTO legacy = dozerMapper.map(formLegacy, LegacyDTO.class);
			
			
			
			
			legacy.setEmpresa(empresaDTO);
			legacy.setProyecto(dozerMapper.map(proyectoDAO.findProyectoByProyectId(formLegacy.getIdProyecto()),ProyectoDTO.class ));
			legacy.setMaquina(dozerMapper.map(maquinaDAO.getByKey(formLegacy.getIdMaquina()),MaquinaDTO.class));
			legacyDTO.add(legacy);
		}
		
		
		
		mav.addObject("formLegacys", legacyDTO);

		if (empresa1.getId() == user.getEmpresa_id()) {
			// mav.setViewName("user/miEmpresa");
			mav.setViewName("user/cPanel/cPanelIndex");

			empresaDTO.setUrlImagen(ImageConverterUtility.convertImage(empresa.getUrlImagen()));
			mav.addObject("empresaDTO", empresaDTO);

			return mav;
		} else {
			mav.setViewName("error/errorGenerico");
			mav.addObject("mensaje", "Se produjo un error.");
			mav.addObject("url", "userIni.htm");

			return mav;

		}

	}

	@RequestMapping("user/cpanelIndex")
	public ModelAndView cPanelIndex() {

		ModelAndView mav = new ModelAndView("user/cPanel/cPanelIndex");

		return mav;

	}

}
