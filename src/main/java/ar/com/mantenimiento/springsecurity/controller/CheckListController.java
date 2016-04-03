package ar.com.mantenimiento.springsecurity.controller;

import java.io.IOException;
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
import ar.com.mantenimiento.dto.FormDTO;
import ar.com.mantenimiento.dto.MaquinaDTO;
import ar.com.mantenimiento.dto.MaquinaProyectoIdDTO;
import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.Form;
import ar.com.mantenimiento.entity.Maquina;
import ar.com.mantenimiento.entity.Proyecto;
import ar.com.mantenimiento.services.LegacyService;
import ar.com.mantenimiento.springsecurity.dao.impl.EPPDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.FormDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.MaquinaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.ProyectoDAO;
import ar.com.mantenimiento.utility.GsonUtility;
import ar.com.mantenimiento.utility.ImageConverterUtility;

@Controller
@Transactional
public class CheckListController {

	@Autowired
	private ProyectoDAO proyectoDAO;

	@Autowired
	private EmpresaDAO empresaDAO;

	@Autowired
	private Mapper dozerMapper;

	@Autowired
	private MaquinaDAO maquinaDAO;

	@Autowired
	private FormDAO formDAO;

	@Autowired
	private GsonUtility gsonUtility;

	@Autowired
	private EPPDAO EPPDAO;

	@Autowired
	private LegacyService legacyService;

	@RequestMapping("operario/getCheckListById.htm")
	public ModelAndView getCheckListById(MaquinaProyectoIdDTO maquinaProyectoId) throws IOException {

		ModelAndView mav = new ModelAndView("operario/checklist/checklist");

		Proyecto proyecto = proyectoDAO.findProyectoByProyectId(maquinaProyectoId.getProyectoId());
		mav.addObject("proyecto", proyecto);

		Empresa empresa = empresaDAO.findEmpresaByProyectId(maquinaProyectoId.getProyectoId());

		EmpresaDTO empresaDTO = dozerMapper.map(empresa, EmpresaDTO.class);

		empresaDTO.setUrlImagen(ImageConverterUtility.convertImage(empresa.getUrlImagen()));
		mav.addObject("empresa", empresaDTO);

		Form form = formDAO.findFormByMaquinaId(maquinaProyectoId.getMaquinaId());
		FormDTO formDTO = dozerMapper.map(form, FormDTO.class);

		Maquina maquina = maquinaDAO.findMaquinaById(maquinaProyectoId.getMaquinaId());
		MaquinaDTO maquinaDTO = dozerMapper.map(maquina, MaquinaDTO.class);

		List<EPPDTO> epps = EPPDAO.findEppByFormId(form.getId());

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

		mav.addObject("eppObligatorio", obligatorio);
		mav.addObject("eppOpcional", opcional);

		mav.addObject("maquina", maquinaDTO);

		mav.addObject("form", formDTO);

		return mav;

	}

	@RequestMapping("operario/submitChecklist.htm")
	public @ResponseBody String submitChecklist(String form) {

		FormDTO formDTO = gsonUtility.getGson().fromJson(form, FormDTO.class);

		legacyService.saveFormLegacy(formDTO);

		// FIXME
		// guardar en base de datos
		return null;
	}

}
