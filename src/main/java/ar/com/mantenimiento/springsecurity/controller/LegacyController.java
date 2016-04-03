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

import ar.com.mantenimiento.dto.EPPDTO;
import ar.com.mantenimiento.dto.EmpresaDTO;
import ar.com.mantenimiento.dto.FormLegacyDTO;
import ar.com.mantenimiento.dto.MaquinaDTO;
import ar.com.mantenimiento.dto.ProyectoDTO;
import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.Form;
import ar.com.mantenimiento.entity.FormLegacy;
import ar.com.mantenimiento.entity.Maquina;
import ar.com.mantenimiento.entity.Proyecto;
import ar.com.mantenimiento.springsecurity.dao.impl.EPPDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.FormDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.FormLegacyDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.MaquinaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.ProyectoDAO;
import ar.com.mantenimiento.utility.ImageConverterUtility;

@Controller
@Transactional
public class LegacyController {

	@Autowired
	private FormLegacyDAO formLegacyDAO;

	@Autowired
	private EPPDAO eppDAO;
	
	@Autowired
	private EmpresaDAO empresaDAO;

	@Autowired
	private MaquinaDAO maquinaDAO;
	
	@Autowired
	private ProyectoDAO proyectoDAO;
	
	@Autowired
	private Mapper dozerMapper;

	
	
	@Autowired
	private FormDAO formDAO;

	private int requested;

	@RequestMapping("user/saveid.htm")
	public @ResponseBody String saveid(int idFormLegacy) {

		requested = idFormLegacy;
		return "200Ok";
		
	}

	@RequestMapping("user/requestLegacy.htm")
	public ModelAndView requestLegacy() {
		int idFormLegacy = requested;
		ModelAndView mav = new ModelAndView("user/checklist/mostrarChecklist");
		// entregar legacy que es un legacyDTO
		// epp oblitagorios y opcionales para esta maquina eppObligatorio
		// eppOpcional con las imagenes en base64

		FormLegacy formLegacy = formLegacyDAO.getByKey(idFormLegacy);
		
		
		Form form = formDAO.findFormByMaquinaId(formLegacy.getIdMaquina());
		List<EPPDTO> epps = eppDAO.findEppByFormId(form.getId());
		Empresa empresa = empresaDAO.getByKey(formLegacy.getIdEmpresa());
		Maquina maquina = maquinaDAO.getByKey(formLegacy.getIdMaquina());
		Proyecto proyecto = proyectoDAO.getByKey(formLegacy.getIdProyecto());

		List<EPPDTO> obligatorio = new ArrayList<EPPDTO>();
		List<EPPDTO> opcional = new ArrayList<EPPDTO>();
		// FIXME epp dto sacar esto a un utility
		for (EPPDTO eppdto : epps) {

			eppdto.setImagen(ImageConverterUtility.convertImage(eppdto.getImagen()));
			if (eppdto.isObligatorio()) {

				obligatorio.add(eppdto);

			} else {

				opcional.add(eppdto);

			}

		}
		ProyectoDTO proyectoDTO = dozerMapper.map(proyecto, ProyectoDTO.class);
		MaquinaDTO maquinaDTO = dozerMapper.map(maquina, MaquinaDTO.class);
		EmpresaDTO empresaDTO = dozerMapper.map(empresa, EmpresaDTO.class);
		empresaDTO.setUrlImagen(ImageConverterUtility.convertImage(empresa.getUrlImagen()));
		FormLegacyDTO formLegacyDTO = dozerMapper.map(formLegacy, FormLegacyDTO.class);
		
		formLegacyDTO.setEmpresa(empresaDTO);
		formLegacyDTO.setMaquina(maquinaDTO);
		formLegacyDTO.setProyecto(proyectoDTO);
		
		
		
		
		
		
		mav.addObject("eppObligatorio", obligatorio);
		mav.addObject("eppOpcional", opcional);
		mav.addObject("legacy", formLegacyDTO);

		return mav;

	}

}
